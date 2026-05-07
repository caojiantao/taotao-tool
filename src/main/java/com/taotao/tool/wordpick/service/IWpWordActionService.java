package com.taotao.tool.wordpick.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taotao.tool.wordpick.model.WpWordAction;

public interface IWpWordActionService extends IService<WpWordAction> {

    /**
     * 上报一次单词判断行为，写入流水并发布 WordActionEvent。
     *
     * @param userId 用户 ID
     * @param learnSessionId 学习会话 ID
     * @param wordId 单词 ID
     * @param action 行为类型（11=认识 12=不认识）
     */
    void saveAction(Integer userId, Long learnSessionId, Integer wordId, Short action);
}
