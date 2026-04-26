package com.taotao.tool.wordpick.service;

import com.taotao.tool.wordpick.dto.req.WpSaveChapterProgressReq;
import com.taotao.tool.wordpick.dto.resp.WpChapterResp;
import com.taotao.tool.wordpick.model.WpUserChapterProgress;

import java.util.List;

public interface IWpUserChapterProgressService {

    void saveChapterProgress(Integer userId, WpSaveChapterProgressReq req);

    WpUserChapterProgress getChapterProgress(Integer userId, Integer chapterId);

    // 返回当前用户学习中（state=1）的章节列表
    List<WpChapterResp> listLearningChapters(Integer userId);
}
