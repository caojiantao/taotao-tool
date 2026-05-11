package com.taotao.tool.wordpick.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.tool.common.util.TTAssertUtils;
import com.taotao.tool.wordpick.dto.imports.WpWordbookData;
import com.taotao.tool.wordpick.dto.imports.WpWordbookManifest;
import com.taotao.tool.wordpick.dto.resp.WpWordbookImportResp;
import com.taotao.tool.wordpick.mapper.WpCategoryMapper;
import com.taotao.tool.wordpick.mapper.WpChapterMapper;
import com.taotao.tool.wordpick.mapper.WpWordExampleMapper;
import com.taotao.tool.wordpick.mapper.WpWordMapper;
import com.taotao.tool.wordpick.mapper.WpWordSenseMapper;
import com.taotao.tool.wordpick.model.WpCategory;
import com.taotao.tool.wordpick.model.WpChapter;
import com.taotao.tool.wordpick.model.WpWord;
import com.taotao.tool.wordpick.model.WpWordExample;
import com.taotao.tool.wordpick.model.WpWordSense;
import com.taotao.tool.wordpick.service.IWpWordbookImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WpWordbookImportServiceImpl implements IWpWordbookImportService {

    private static final String DATASET_ROOT = "wordpick/data/";

    private final ObjectMapper objectMapper;
    private final WpCategoryMapper categoryMapper;
    private final WpChapterMapper chapterMapper;
    private final WpWordMapper wordMapper;
    private final WpWordSenseMapper senseMapper;
    private final WpWordExampleMapper exampleMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public WpWordbookImportResp importDataset(String dataset) {
        WpWordbookManifest manifest = readJson(dataset, "manifest.json", WpWordbookManifest.class);
        validateManifest(manifest);

        WpCategory category = findOrCreateCategory(manifest.getCategory().getName());
        resetCategory(category.getId());

        ImportCounter counter = rebuildCategory(dataset, category.getId(), manifest);
        return WpWordbookImportResp.builder()
                .dataset(dataset)
                .categoryName(category.getName())
                .chapterCount(counter.chapterCount)
                .wordCount(counter.wordCount)
                .senseCount(counter.senseCount)
                .exampleCount(counter.exampleCount)
                .build();
    }

    private WpCategory findOrCreateCategory(String categoryName) {
        WpCategory category = categoryMapper.selectOne(new LambdaQueryWrapper<WpCategory>()
                .eq(WpCategory::getName, categoryName)
                .last("limit 1"));
        if (category != null) {
            return category;
        }
        WpCategory newCategory = new WpCategory();
        newCategory.setName(categoryName);
        newCategory.setSort(0);
        categoryMapper.insert(newCategory);
        return newCategory;
    }

    private void resetCategory(Integer categoryId) {
        List<WpChapter> chapters = chapterMapper.selectList(new LambdaQueryWrapper<WpChapter>()
                .eq(WpChapter::getCategoryId, categoryId));
        if (chapters.isEmpty()) {
            return;
        }
        List<Integer> chapterIds = chapters.stream().map(WpChapter::getId).collect(Collectors.toList());
        List<WpWord> words = wordMapper.selectList(new LambdaQueryWrapper<WpWord>()
                .in(WpWord::getChapterId, chapterIds));
        if (!words.isEmpty()) {
            List<Integer> wordIds = words.stream().map(WpWord::getId).collect(Collectors.toList());
            exampleMapper.delete(new LambdaQueryWrapper<WpWordExample>().in(WpWordExample::getWordId, wordIds));
            senseMapper.delete(new LambdaQueryWrapper<WpWordSense>().in(WpWordSense::getWordId, wordIds));
        }
        wordMapper.delete(new LambdaQueryWrapper<WpWord>().in(WpWord::getChapterId, chapterIds));
        chapterMapper.delete(new LambdaQueryWrapper<WpChapter>().eq(WpChapter::getCategoryId, categoryId));
    }

    private ImportCounter rebuildCategory(String dataset, Integer categoryId, WpWordbookManifest manifest) {
        ImportCounter counter = new ImportCounter();
        List<WpWordbookManifest.Chapter> chapters = manifest.getChapters();
        for (int chapterIndex = 0; chapterIndex < chapters.size(); chapterIndex++) {
            WpWordbookManifest.Chapter chapterRef = chapters.get(chapterIndex);
            validateChapterRef(chapterRef);
            WpWordbookData chapterData = readJson(dataset, chapterRef.getChapterFile(), WpWordbookData.class);
            validateChapterData(chapterRef, chapterData);

            WpChapter chapter = new WpChapter();
            chapter.setCategoryId(categoryId);
            chapter.setName(chapterRef.getName());
            chapter.setSort(chapterIndex + 1);
            chapterMapper.insert(chapter);
            counter.chapterCount++;

            List<WpWordbookData.Word> words = chapterData.getWords();
            for (int wordIndex = 0; wordIndex < words.size(); wordIndex++) {
                WpWordbookData.Word wordData = words.get(wordIndex);
                validateWord(wordData);

                WpWord word = new WpWord();
                word.setChapterId(chapter.getId());
                word.setWord(wordData.getWord());
                word.setPhoneticUs(wordData.getPhoneticUs());
                word.setPhoneticUk(wordData.getPhoneticUk());
                word.setSort(wordIndex + 1);
                wordMapper.insert(word);
                counter.wordCount++;

                counter.senseCount += insertSenses(word.getId(), wordData.getSenses());
                counter.exampleCount += insertExamples(word.getId(), wordData.getExamples());
            }
        }
        return counter;
    }

    private int insertSenses(Integer wordId, List<WpWordbookData.Sense> senses) {
        if (CollectionUtils.isEmpty(senses)) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < senses.size(); i++) {
            WpWordbookData.Sense senseData = senses.get(i);
            TTAssertUtils.isTrue(StringUtils.hasText(senseData.getMeaning()), "释义不能为空");
            WpWordSense sense = new WpWordSense();
            sense.setWordId(wordId);
            sense.setPos(senseData.getPos());
            sense.setMeaning(senseData.getMeaning());
            sense.setSort(i + 1);
            senseMapper.insert(sense);
            count++;
        }
        return count;
    }

    private int insertExamples(Integer wordId, List<WpWordbookData.Example> examples) {
        if (CollectionUtils.isEmpty(examples)) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < examples.size(); i++) {
            WpWordbookData.Example exampleData = examples.get(i);
            TTAssertUtils.isTrue(StringUtils.hasText(exampleData.getSentence()), "例句不能为空");
            WpWordExample example = new WpWordExample();
            example.setWordId(wordId);
            example.setSentence(exampleData.getSentence());
            example.setTranslation(exampleData.getTranslation());
            example.setSort(i + 1);
            exampleMapper.insert(example);
            count++;
        }
        return count;
    }

    private <T> T readJson(String dataset, String relativePath, Class<T> clazz) {
        ClassPathResource resource = new ClassPathResource(DATASET_ROOT + dataset + "/" + relativePath);
        TTAssertUtils.isTrue(resource.exists(), "词库 JSON 不存在：" + relativePath);
        try {
            return objectMapper.readValue(resource.getInputStream(), clazz);
        } catch (IOException e) {
            throw new IllegalStateException("词库 JSON 解析失败：" + relativePath, e);
        }
    }

    private void validateManifest(WpWordbookManifest manifest) {
        TTAssertUtils.notNull(manifest, "manifest 不能为空");
        TTAssertUtils.notNull(manifest.getCategory(), "category 不能为空");
        TTAssertUtils.isTrue(StringUtils.hasText(manifest.getCategory().getName()), "category.name 不能为空");
        TTAssertUtils.isTrue(!CollectionUtils.isEmpty(manifest.getChapters()), "chapters 不能为空");
    }

    private void validateChapterRef(WpWordbookManifest.Chapter chapterRef) {
        TTAssertUtils.notNull(chapterRef, "chapter 不能为空");
        TTAssertUtils.isTrue(StringUtils.hasText(chapterRef.getName()), "chapter.name 不能为空");
        TTAssertUtils.isTrue(StringUtils.hasText(chapterRef.getChapterFile()), "chapterFile 不能为空");
        TTAssertUtils.notTrue(chapterRef.getChapterFile().contains(".."), "chapterFile 格式错误");
        TTAssertUtils.notTrue(chapterRef.getChapterFile().startsWith("/"), "chapterFile 格式错误");
    }

    private void validateChapterData(WpWordbookManifest.Chapter chapterRef, WpWordbookData chapterData) {
        TTAssertUtils.notNull(chapterData, "章节数据不能为空");
        TTAssertUtils.isTrue(Objects.equals(chapterRef.getName(), chapterData.getName()), "章节名称不一致：" + chapterRef.getName());
        TTAssertUtils.isTrue(!CollectionUtils.isEmpty(chapterData.getWords()), "章节单词不能为空：" + chapterRef.getName());
    }

    private void validateWord(WpWordbookData.Word wordData) {
        TTAssertUtils.notNull(wordData, "单词不能为空");
        TTAssertUtils.isTrue(StringUtils.hasText(wordData.getWord()), "单词拼写不能为空");
    }

    private static class ImportCounter {
        private int chapterCount;
        private int wordCount;
        private int senseCount;
        private int exampleCount;
    }
}
