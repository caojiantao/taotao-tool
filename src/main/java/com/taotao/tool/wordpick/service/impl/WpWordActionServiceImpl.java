package com.taotao.tool.wordpick.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taotao.tool.common.util.TTAssertUtils;
import com.taotao.tool.wordpick.event.WordActionEvent;
import com.taotao.tool.wordpick.mapper.WpLearnSessionMapper;
import com.taotao.tool.wordpick.mapper.WpWordActionMapper;
import com.taotao.tool.wordpick.model.WpLearnSession;
import com.taotao.tool.wordpick.model.WpWordAction;
import com.taotao.tool.wordpick.service.IWpWordActionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class WpWordActionServiceImpl
        extends ServiceImpl<WpWordActionMapper, WpWordAction>
        implements IWpWordActionService {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private WpLearnSessionMapper learnSessionMapper;

    @Override
    public void saveAction(Integer userId, Long learnSessionId, Integer wordId, Short action) {
        assertSessionOwner(userId, learnSessionId);

        WpWordAction newAction = new WpWordAction();
        newAction.setUserId(userId);
        newAction.setLearnSessionId(learnSessionId);
        newAction.setWordId(wordId);
        newAction.setAction(action);
        newAction.setCreatedAt(LocalDateTime.now());
        save(newAction);
        log.info("saveWordAction_done, userId={}, learnSessionId={}, wordId={}, action={}",
                userId, learnSessionId, wordId, action);

        // 同步发布事件，监听器更新用户章节学习摘要等派生数据
        eventPublisher.publishEvent(new WordActionEvent(this, newAction));
    }

    private void assertSessionOwner(Integer userId, Long learnSessionId) {
        if (learnSessionId == null) {
            return;
        }
        WpLearnSession session = learnSessionMapper.selectById(learnSessionId);
        TTAssertUtils.notNull(session, "学习会话不存在");
        TTAssertUtils.isTrue(userId.equals(session.getUserId()), "学习会话不属于当前用户");
    }

}
