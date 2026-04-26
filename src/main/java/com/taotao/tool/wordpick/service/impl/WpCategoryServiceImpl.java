package com.taotao.tool.wordpick.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taotao.tool.wordpick.dto.resp.WpCategoryResp;
import com.taotao.tool.wordpick.dto.resp.WpChapterResp;
import com.taotao.tool.wordpick.dto.resp.WpWordResp;
import com.taotao.tool.wordpick.mapper.WpCategoryMapper;
import com.taotao.tool.wordpick.mapper.WpChapterMapper;
import com.taotao.tool.wordpick.mapper.WpWordMapper;
import com.taotao.tool.wordpick.model.WpCategory;
import com.taotao.tool.wordpick.model.WpChapter;
import com.taotao.tool.wordpick.model.WpWord;
import com.taotao.tool.wordpick.service.IWpCategoryService;
import com.taotao.tool.wordpick.service.IWpWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WpCategoryServiceImpl extends ServiceImpl<WpCategoryMapper, WpCategory>
        implements IWpCategoryService {

    @Autowired
    private WpChapterMapper chapterMapper;

    @Autowired
    private WpWordMapper wordMapper;

    @Autowired
    private IWpWordService wordService;

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
        return wordService.listWordRespByIds(wordIds);
    }
}
