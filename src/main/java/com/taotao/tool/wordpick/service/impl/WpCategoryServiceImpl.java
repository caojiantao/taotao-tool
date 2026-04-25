package com.taotao.tool.wordpick.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taotao.tool.wordpick.dto.resp.WpCategoryResp;
import com.taotao.tool.wordpick.dto.resp.WpChapterResp;
import com.taotao.tool.wordpick.dto.resp.WpWordResp;
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
import com.taotao.tool.wordpick.service.IWpCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WpCategoryServiceImpl extends ServiceImpl<WpCategoryMapper, WpCategory>
        implements IWpCategoryService {

    @Autowired
    private WpChapterMapper chapterMapper;

    @Autowired
    private WpWordMapper wordMapper;

    @Autowired
    private WpWordSenseMapper senseMapper;

    @Autowired
    private WpWordExampleMapper exampleMapper;

    @Override
    public List<WpCategoryResp> listCategories() {
        List<WpCategory> list = list(new LambdaQueryWrapper<WpCategory>().orderByAsc(WpCategory::getSort));
        List<WpCategoryResp> result = new ArrayList<>();
        for (WpCategory c : list) {
            WpCategoryResp resp = new WpCategoryResp();
            resp.setId(c.getId());
            resp.setName(c.getName());
            resp.setIcon(c.getIcon());
            resp.setSort(c.getSort());
            result.add(resp);
        }
        return result;
    }

    @Override
    public List<WpChapterResp> listChapters(Integer categoryId) {
        List<WpChapter> list = chapterMapper.selectList(
                new LambdaQueryWrapper<WpChapter>()
                        .eq(WpChapter::getCategoryId, categoryId)
                        .orderByAsc(WpChapter::getSort));
        List<WpChapterResp> result = new ArrayList<>();
        for (WpChapter c : list) {
            WpChapterResp resp = new WpChapterResp();
            resp.setId(c.getId());
            resp.setName(c.getName());
            resp.setSort(c.getSort());
            result.add(resp);
        }
        return result;
    }

    @Override
    public List<WpWordResp> listWords(Integer chapterId) {
        List<WpWord> words = wordMapper.selectList(
                new LambdaQueryWrapper<WpWord>()
                        .eq(WpWord::getChapterId, chapterId)
                        .orderByAsc(WpWord::getSort));

        if (words.isEmpty()) {
            return Collections.emptyList();
        }

        List<Integer> wordIds = words.stream().map(WpWord::getId).collect(Collectors.toList());

        List<WpWordSense> senses = senseMapper.selectList(
                new LambdaQueryWrapper<WpWordSense>()
                        .in(WpWordSense::getWordId, wordIds)
                        .orderByAsc(WpWordSense::getSort));

        List<WpWordExample> examples = exampleMapper.selectList(
                new LambdaQueryWrapper<WpWordExample>()
                        .in(WpWordExample::getWordId, wordIds)
                        .orderByAsc(WpWordExample::getSort));

        Map<Integer, List<WpWordSense>> senseMap = senses.stream()
                .collect(Collectors.groupingBy(WpWordSense::getWordId));
        Map<Integer, List<WpWordExample>> exampleMap = examples.stream()
                .collect(Collectors.groupingBy(WpWordExample::getWordId));

        List<WpWordResp> result = new ArrayList<>();
        for (WpWord w : words) {
            WpWordResp resp = new WpWordResp();
            resp.setId(w.getId());
            resp.setWord(w.getWord());
            resp.setPhoneticUs(w.getPhoneticUs());
            resp.setPhoneticUk(w.getPhoneticUk());
            resp.setSort(w.getSort());

            List<WpWordResp.WpSenseResp> senseResps = new ArrayList<>();
            for (WpWordSense s : senseMap.getOrDefault(w.getId(), Collections.emptyList())) {
                WpWordResp.WpSenseResp sr = new WpWordResp.WpSenseResp();
                sr.setPos(s.getPos());
                sr.setMeaning(s.getMeaning());
                senseResps.add(sr);
            }
            resp.setSenses(senseResps);

            List<WpWordResp.WpExampleResp> exampleResps = new ArrayList<>();
            for (WpWordExample e : exampleMap.getOrDefault(w.getId(), Collections.emptyList())) {
                WpWordResp.WpExampleResp er = new WpWordResp.WpExampleResp();
                er.setSentence(e.getSentence());
                er.setTranslation(e.getTranslation());
                exampleResps.add(er);
            }
            resp.setExamples(exampleResps);

            result.add(resp);
        }
        return result;
    }
}
