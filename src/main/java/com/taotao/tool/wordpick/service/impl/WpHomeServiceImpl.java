package com.taotao.tool.wordpick.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.taotao.tool.common.util.DateTimeUtils;
import com.taotao.tool.wordpick.cache.ChapterLearnSummaryCache;
import com.taotao.tool.wordpick.dto.resp.WpHomeResp;
import com.taotao.tool.wordpick.dto.resp.WpHomeSummaryResp;
import com.taotao.tool.wordpick.mapper.WpLearnSessionMapper;
import com.taotao.tool.wordpick.mapper.WpWordActionMapper;
import com.taotao.tool.wordpick.mapper.WpWordMapper;
import com.taotao.tool.wordpick.model.WpLearnSession;
import com.taotao.tool.wordpick.model.WpWord;
import com.taotao.tool.wordpick.model.WpWordAction;
import com.taotao.tool.wordpick.service.IWpCategoryService;
import com.taotao.tool.wordpick.service.IWpHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class WpHomeServiceImpl implements IWpHomeService {

    private static final Integer STATE_FINISHED = 2;

    @Autowired
    private IWpCategoryService categoryService;

    @Autowired
    private WpWordActionMapper wordActionMapper;

    @Autowired
    private WpWordMapper wordMapper;

    @Autowired
    private WpLearnSessionMapper learnSessionMapper;

    @Override
    public WpHomeResp getHome(Integer userId) {
        return WpHomeResp.builder()
                .summary(summary(userId))
                .categories(categoryService.listCategories())
                .build();
    }

    private WpHomeSummaryResp summary(Integer userId) {
        List<WpWordAction> actions = wordActionMapper.selectList(new LambdaQueryWrapper<WpWordAction>()
                .eq(WpWordAction::getUserId, userId)
                .between(WpWordAction::getAction,
                        ChapterLearnSummaryCache.JUDGE_ACTION_MIN,
                        ChapterLearnSummaryCache.JUDGE_ACTION_MAX));
        Set<Integer> wordIds = actions.stream()
                .map(WpWordAction::getWordId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        Set<Integer> chapterIds = learnedChapterIds(wordIds);
        return WpHomeSummaryResp.builder()
                .learnedWordActionCount(actions.size())
                .learnedChapterCount(chapterIds.size())
                .durationText(DateTimeUtils.formatDuration(durationSeconds(userId)))
                .build();
    }

    private Set<Integer> learnedChapterIds(Set<Integer> wordIds) {
        if (wordIds.isEmpty()) {
            return Collections.emptySet();
        }
        List<WpWord> words = wordMapper.selectList(new LambdaQueryWrapper<WpWord>()
                .in(WpWord::getId, wordIds));
        return words.stream()
                .map(WpWord::getChapterId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    private Integer durationSeconds(Integer userId) {
        List<WpLearnSession> sessions = learnSessionMapper.selectList(new LambdaQueryWrapper<WpLearnSession>()
                .eq(WpLearnSession::getUserId, userId)
                .eq(WpLearnSession::getState, STATE_FINISHED)
                .isNotNull(WpLearnSession::getDurationSeconds));
        int seconds = sessions.stream()
                .map(WpLearnSession::getDurationSeconds)
                .filter(Objects::nonNull)
                .mapToInt(Integer::intValue)
                .sum();
        return Math.max(0, seconds);
    }
}
