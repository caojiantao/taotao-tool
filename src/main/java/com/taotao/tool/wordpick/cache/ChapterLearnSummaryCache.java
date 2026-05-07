package com.taotao.tool.wordpick.cache;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.taotao.tool.wordpick.event.WordActionEvent;
import com.taotao.tool.wordpick.model.WpWord;
import com.taotao.tool.wordpick.model.WpWordAction;
import com.taotao.tool.wordpick.service.IWpWordActionService;
import com.taotao.tool.wordpick.service.IWpWordService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用户章节学习摘要缓存（计数式 + 事件溯源重放）。
 * <p>
 * 缓存按 userId -> chapterId 组织，存用户-章节维度的学习聚合。
 * session 记录单次学习流，这里记录某用户在某章节上的累计事实。
 * 启动时游标遍历 log 表，逐条 apply；增量写入时复用同一个 apply。
 */
@Slf4j
@Component
public class ChapterLearnSummaryCache {

    public static final short JUDGE_ACTION_MIN = 11;
    public static final short JUDGE_ACTION_MAX = 19;
    public static final Short ACTION_KNOWN = 11;
    public static final Short ACTION_UNKNOWN = 12;

    private static final int INIT_BATCH_SIZE = 1000;

    private final Map<Integer, Map<Integer, ChapterLearnSummary>> cache = new ConcurrentHashMap<>();

    @Autowired
    private IWpWordActionService wordActionService;

    @Autowired
    private IWpWordService wordService;

    /**
     * 启动时按 id 顺序遍历 log 表，逐条重放为 apply 调用。
     */
    @PostConstruct
    public void initCache() {
        long lastId = 0L;
        long count = 0L;
        while (true) {
            List<WpWordAction> batch = wordActionService.list(
                    new LambdaQueryWrapper<WpWordAction>()
                            .gt(WpWordAction::getId, lastId)
                            .between(WpWordAction::getAction, JUDGE_ACTION_MIN, JUDGE_ACTION_MAX)
                            .orderByAsc(WpWordAction::getId)
                            .last("LIMIT " + INIT_BATCH_SIZE));
            if (batch.isEmpty()) {
                break;
            }
            for (WpWordAction record : batch) {
                apply(new WordActionEvent(this, record));
                lastId = record.getId();
                count++;
            }
        }
        log.info("ChapterLearnSummaryCache_initialized, replayedCount={}, cacheSize={}",
                count, cacheSize());
    }

    /**
     * 应用单条事件到缓存。启动重放和增量更新都调用此方法。
     */
    public void apply(WordActionEvent event) {
        WpWordAction newAction = event.getNewAction();

        Integer chapterId = chapterIdOfWord(newAction.getWordId());
        if (chapterId == null) {
            log.warn("ChapterLearnSummaryCache_apply_word_not_found, wordId={}", newAction.getWordId());
            return;
        }

        Map<Integer, ChapterLearnSummary> userCache = cache.computeIfAbsent(
                newAction.getUserId(), k -> new ConcurrentHashMap<>());
        ChapterLearnSummary state = userCache.computeIfAbsent(chapterId,
                k -> new ChapterLearnSummary());

        synchronized (state) {
            Short newType = newAction.getAction();

            if (Objects.equals(newType, ACTION_KNOWN)) {
                state.knownActionCount++;
            }
            if (Objects.equals(newType, ACTION_UNKNOWN)) {
                state.unknownActionCount++;
            }

            state.lastLearnTime = newAction.getCreatedAt();
        }
    }

    public ChapterLearnSummary get(Integer userId, Integer chapterId) {
        Map<Integer, ChapterLearnSummary> userCache = cache.get(userId);
        return userCache == null ? null : userCache.get(chapterId);
    }

    private Integer chapterIdOfWord(Integer wordId) {
        WpWord word = wordService.getById(wordId);
        return word != null ? word.getChapterId() : null;
    }

    private int cacheSize() {
        return cache.values().stream().mapToInt(Map::size).sum();
    }

    /**
     * 用户-章节维度的学习摘要。
     */
    @Getter
    @Setter
    public static class ChapterLearnSummary {
        private int knownActionCount;
        private int unknownActionCount;
        private LocalDateTime lastLearnTime;
    }
}
