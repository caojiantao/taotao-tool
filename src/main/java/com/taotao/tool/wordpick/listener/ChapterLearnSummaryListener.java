package com.taotao.tool.wordpick.listener;

import com.taotao.tool.wordpick.cache.ChapterLearnSummaryCache;
import com.taotao.tool.wordpick.event.WordActionEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 监听 WordActionEvent，把事件应用到用户章节学习摘要缓存。
 */
@Slf4j
@Component
public class ChapterLearnSummaryListener {

    @Autowired
    private ChapterLearnSummaryCache cache;

    @EventListener
    public void onWordAction(WordActionEvent event) {
        cache.apply(event);
    }
}
