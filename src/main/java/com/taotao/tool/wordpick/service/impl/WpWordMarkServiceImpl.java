package com.taotao.tool.wordpick.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taotao.tool.wordpick.dto.req.WpSaveMarkReq;
import com.taotao.tool.wordpick.dto.resp.WpWordResp;
import com.taotao.tool.wordpick.mapper.WpChapterMapper;
import com.taotao.tool.wordpick.mapper.WpUserWordMarkMapper;
import com.taotao.tool.wordpick.mapper.WpWordMapper;
import com.taotao.tool.wordpick.model.WpChapter;
import com.taotao.tool.wordpick.model.WpUserWordMark;
import com.taotao.tool.wordpick.model.WpWord;
import com.taotao.tool.wordpick.service.IWpWordMarkService;
import com.taotao.tool.wordpick.service.IWpWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WpWordMarkServiceImpl extends ServiceImpl<WpUserWordMarkMapper, WpUserWordMark>
        implements IWpWordMarkService {

    @Autowired
    private WpWordMapper wordMapper;

    @Autowired
    private WpChapterMapper chapterMapper;

    @Autowired
    private IWpWordService wordService;

    @Override
    public void saveMark(Integer userId, WpSaveMarkReq req) {
        // 反查章节，获取 categoryId 冗余存储
        WpWord word = wordMapper.selectById(req.getWordId());
        WpChapter chapter = chapterMapper.selectById(word.getChapterId());

        // upsert：已有记录更新状态，否则新增
        WpUserWordMark existing = getOne(new LambdaQueryWrapper<WpUserWordMark>()
                .eq(WpUserWordMark::getUserId, userId)
                .eq(WpUserWordMark::getWordId, req.getWordId()));
        if (existing != null) {
            existing.setState(req.getState());
            updateById(existing);
        } else {
            WpUserWordMark mark = new WpUserWordMark();
            mark.setUserId(userId);
            mark.setWordId(req.getWordId());
            mark.setCategoryId(chapter.getCategoryId());
            mark.setState(req.getState());
            save(mark);
        }
    }

    @Override
    public List<WpWordResp> listMark(Integer userId, Integer categoryId, Integer state) {
        // categoryId、state 为空时不过滤对应条件
        LambdaQueryWrapper<WpUserWordMark> wrapper = new LambdaQueryWrapper<WpUserWordMark>()
                .eq(WpUserWordMark::getUserId, userId)
                .eq(categoryId != null, WpUserWordMark::getCategoryId, categoryId)
                .eq(state != null, WpUserWordMark::getState, state);
        List<WpUserWordMark> marks = list(wrapper);

        if (marks.isEmpty()) {
            return Collections.emptyList();
        }

        List<Integer> wordIds = marks.stream().map(WpUserWordMark::getWordId).collect(Collectors.toList());
        return wordService.listWordRespByIds(wordIds);
    }
}
