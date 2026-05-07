package com.taotao.tool.wordpick.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taotao.tool.common.util.TTAssertUtils;
import com.taotao.tool.wordpick.dto.resp.WpLearnSessionResp;
import com.taotao.tool.wordpick.mapper.WpLearnSessionMapper;
import com.taotao.tool.wordpick.model.WpLearnSession;
import com.taotao.tool.wordpick.service.IWpLearnSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class WpLearnSessionServiceImpl
        extends ServiceImpl<WpLearnSessionMapper, WpLearnSession>
        implements IWpLearnSessionService {

    @Override
    public WpLearnSessionResp startSession(Integer userId) {
        WpLearnSession session = new WpLearnSession();
        session.setUserId(userId);
        session.setStartedAt(LocalDateTime.now());
        session.setKnownActionCount(0);
        session.setKnownWordCount(0);
        session.setUnknownActionCount(0);
        session.setUnknownWordCount(0);
        session.setState(STATE_LEARNING);
        save(session);
        return toResp(session);
    }

    @Override
    public WpLearnSessionResp finishSession(Integer userId, Long learnSessionId) {
        WpLearnSession session = getOwnedSession(userId, learnSessionId);
        LocalDateTime finishedAt = LocalDateTime.now();
        session.setFinishedAt(finishedAt);
        session.setDurationSeconds((int) Math.max(0,
                Duration.between(session.getStartedAt(), finishedAt).getSeconds()));
        session.setState(STATE_FINISHED);
        updateById(session);

        return toResp(session);
    }

    @Override
    public void assertSessionOwner(Integer userId, Long learnSessionId) {
        if (learnSessionId == null) {
            return;
        }
        getOwnedSession(userId, learnSessionId);
    }

    private WpLearnSession getOwnedSession(Integer userId, Long learnSessionId) {
        TTAssertUtils.notNull(learnSessionId, "学习会话不存在");
        WpLearnSession session = getById(learnSessionId);
        TTAssertUtils.notNull(session, "学习会话不存在");
        TTAssertUtils.isTrue(Objects.equals(session.getUserId(), userId), "学习会话不属于当前用户");
        return session;
    }

    private WpLearnSessionResp toResp(WpLearnSession session) {
        return WpLearnSessionResp.builder()
                .learnSessionId(session.getId())
                .startedAt(session.getStartedAt())
                .finishedAt(session.getFinishedAt())
                .durationSeconds(session.getDurationSeconds())
                .knownActionCount(session.getKnownActionCount())
                .knownWordCount(session.getKnownWordCount())
                .unknownActionCount(session.getUnknownActionCount())
                .unknownWordCount(session.getUnknownWordCount())
                .state(session.getState())
                .build();
    }

}
