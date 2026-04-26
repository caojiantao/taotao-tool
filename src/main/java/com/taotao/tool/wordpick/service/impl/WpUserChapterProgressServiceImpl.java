package com.taotao.tool.wordpick.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taotao.tool.wordpick.dto.req.WpSaveChapterProgressReq;
import com.taotao.tool.wordpick.dto.resp.WpChapterResp;
import com.taotao.tool.wordpick.mapper.WpChapterMapper;
import com.taotao.tool.wordpick.mapper.WpUserChapterProgressMapper;
import com.taotao.tool.wordpick.model.WpChapter;
import com.taotao.tool.wordpick.model.WpUserChapterProgress;
import com.taotao.tool.wordpick.service.IWpUserChapterProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WpUserChapterProgressServiceImpl
        extends ServiceImpl<WpUserChapterProgressMapper, WpUserChapterProgress>
        implements IWpUserChapterProgressService {

    @Autowired
    private WpChapterMapper chapterMapper;

    @Override
    public void saveChapterProgress(Integer userId, WpSaveChapterProgressReq req) {
        WpUserChapterProgress existing = getOne(new LambdaQueryWrapper<WpUserChapterProgress>()
                .eq(WpUserChapterProgress::getUserId, userId)
                .eq(WpUserChapterProgress::getChapterId, req.getChapterId()));
        if (existing != null) {
            existing.setState(req.getState());
            updateById(existing);
        } else {
            WpUserChapterProgress progress = new WpUserChapterProgress();
            progress.setUserId(userId);
            progress.setChapterId(req.getChapterId());
            progress.setState(req.getState());
            save(progress);
        }
    }

    @Override
    public WpUserChapterProgress getChapterProgress(Integer userId, Integer chapterId) {
        return getOne(new LambdaQueryWrapper<WpUserChapterProgress>()
                .eq(WpUserChapterProgress::getUserId, userId)
                .eq(WpUserChapterProgress::getChapterId, chapterId));
    }

    @Override
    public List<WpChapterResp> listLearningChapters(Integer userId) {
        // 查出学习中的进度记录
        List<WpUserChapterProgress> progresses = list(new LambdaQueryWrapper<WpUserChapterProgress>()
                .eq(WpUserChapterProgress::getUserId, userId)
                .eq(WpUserChapterProgress::getState, 1));
        if (progresses.isEmpty()) {
            return Collections.emptyList();
        }
        // 批量查章节信息
        List<Integer> chapterIds = progresses.stream()
                .map(WpUserChapterProgress::getChapterId)
                .collect(Collectors.toList());
        List<WpChapter> chapters = chapterMapper.selectBatchIds(chapterIds);
        Map<Integer, String> chapterNameMap = chapters.stream()
                .collect(Collectors.toMap(WpChapter::getId, WpChapter::getName));
        // 按 updatedTime 倒序返回
        return progresses.stream()
                .sorted((a, b) -> b.getUpdatedTime().compareTo(a.getUpdatedTime()))
                .map(p -> WpChapterResp.builder()
                        .id(p.getChapterId())
                        .name(chapterNameMap.getOrDefault(p.getChapterId(), ""))
                        .build())
                .collect(Collectors.toList());
    }
}
