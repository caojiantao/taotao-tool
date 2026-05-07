package com.taotao.tool.wordpick.listener;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.taotao.tool.wordpick.cache.ChapterLearnSummaryCache;
import com.taotao.tool.wordpick.event.WordActionEvent;
import com.taotao.tool.wordpick.mapper.WpLearnSessionMapper;
import com.taotao.tool.wordpick.model.WpLearnSession;
import com.taotao.tool.wordpick.model.WpWordAction;
import com.taotao.tool.wordpick.service.IWpWordActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class LearnSessionListener {

    @Autowired
    private WpLearnSessionMapper learnSessionMapper;

    @Autowired
    private IWpWordActionService wordActionService;

    @EventListener
    public void onWordAction(WordActionEvent event) {
        WpWordAction action = event.getNewAction();
        Long learnSessionId = action.getLearnSessionId();
        if (learnSessionId == null) {
            return;
        }
        WpLearnSession session = learnSessionMapper.selectById(learnSessionId);
        if (session == null) {
            return;
        }

        boolean firstActionForWord = isFirstActionForWord(action);
        if (ChapterLearnSummaryCache.ACTION_KNOWN.equals(action.getAction())) {
            session.setKnownActionCount(safe(session.getKnownActionCount()) + 1);
            if (firstActionForWord) {
                session.setKnownWordCount(safe(session.getKnownWordCount()) + 1);
            }
        } else if (ChapterLearnSummaryCache.ACTION_UNKNOWN.equals(action.getAction())) {
            session.setUnknownActionCount(safe(session.getUnknownActionCount()) + 1);
            if (firstActionForWord) {
                session.setUnknownWordCount(safe(session.getUnknownWordCount()) + 1);
            }
        }
        learnSessionMapper.updateById(session);
    }

    private boolean isFirstActionForWord(WpWordAction action) {
        return wordActionService.count(new LambdaQueryWrapper<WpWordAction>()
                .eq(WpWordAction::getUserId, action.getUserId())
                .eq(WpWordAction::getLearnSessionId, action.getLearnSessionId())
                .eq(WpWordAction::getWordId, action.getWordId())
                .eq(WpWordAction::getAction, action.getAction())
                .lt(WpWordAction::getId, action.getId())) == 0;
    }

    private int safe(Integer value) {
        return value == null ? 0 : value;
    }
}
