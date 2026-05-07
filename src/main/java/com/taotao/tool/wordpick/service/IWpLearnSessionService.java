package com.taotao.tool.wordpick.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taotao.tool.wordpick.dto.resp.WpLearnSessionResp;
import com.taotao.tool.wordpick.model.WpLearnSession;

public interface IWpLearnSessionService extends IService<WpLearnSession> {

    Integer STATE_LEARNING = 1;
    Integer STATE_FINISHED = 2;

    WpLearnSessionResp startSession(Integer userId);

    WpLearnSessionResp finishSession(Integer userId, Long learnSessionId);

    void assertSessionOwner(Integer userId, Long learnSessionId);
}
