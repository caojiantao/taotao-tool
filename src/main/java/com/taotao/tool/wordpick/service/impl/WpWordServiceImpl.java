package com.taotao.tool.wordpick.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.taotao.tool.wordpick.dto.resp.WpWordResp;
import com.taotao.tool.wordpick.mapper.WpWordExampleMapper;
import com.taotao.tool.wordpick.mapper.WpWordMapper;
import com.taotao.tool.wordpick.mapper.WpWordSenseMapper;
import com.taotao.tool.wordpick.model.WpWord;
import com.taotao.tool.wordpick.model.WpWordExample;
import com.taotao.tool.wordpick.model.WpWordSense;
import com.taotao.tool.wordpick.service.IWpWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WpWordServiceImpl implements IWpWordService {

    @Autowired
    private WpWordMapper wordMapper;

    @Autowired
    private WpWordSenseMapper senseMapper;

    @Autowired
    private WpWordExampleMapper exampleMapper;

    @Override
    public List<WpWordResp> listWordRespByIds(List<Integer> wordIds) {
        if (wordIds == null || wordIds.isEmpty()) {
            return Collections.emptyList();
        }

        List<WpWord> words = wordMapper.selectList(
                new LambdaQueryWrapper<WpWord>().in(WpWord::getId, wordIds).orderByAsc(WpWord::getSort));

        List<WpWordSense> senses = senseMapper.selectList(
                new LambdaQueryWrapper<WpWordSense>().in(WpWordSense::getWordId, wordIds).orderByAsc(WpWordSense::getSort));

        List<WpWordExample> examples = exampleMapper.selectList(
                new LambdaQueryWrapper<WpWordExample>().in(WpWordExample::getWordId, wordIds).orderByAsc(WpWordExample::getSort));

        Map<Integer, List<WpWordSense>> senseMap = senses.stream()
                .collect(Collectors.groupingBy(WpWordSense::getWordId));
        Map<Integer, List<WpWordExample>> exampleMap = examples.stream()
                .collect(Collectors.groupingBy(WpWordExample::getWordId));

        List<WpWordResp> result = new ArrayList<>();
        for (WpWord w : words) {
            WpWordResp resp = new WpWordResp();
            resp.setId(w.getId());
            resp.setWord(w.getWord());
            resp.setPhoneticUs(w.getPhoneticUs());
            resp.setPhoneticUk(w.getPhoneticUk());
            resp.setSort(w.getSort());

            List<WpWordResp.WpSenseResp> senseResps = new ArrayList<>();
            for (WpWordSense s : senseMap.getOrDefault(w.getId(), Collections.emptyList())) {
                WpWordResp.WpSenseResp sr = new WpWordResp.WpSenseResp();
                sr.setPos(s.getPos());
                sr.setMeaning(s.getMeaning());
                senseResps.add(sr);
            }
            resp.setSenses(senseResps);

            List<WpWordResp.WpExampleResp> exampleResps = new ArrayList<>();
            for (WpWordExample e : exampleMap.getOrDefault(w.getId(), Collections.emptyList())) {
                WpWordResp.WpExampleResp er = new WpWordResp.WpExampleResp();
                er.setSentence(e.getSentence());
                er.setTranslation(e.getTranslation());
                exampleResps.add(er);
            }
            resp.setExamples(exampleResps);

            result.add(resp);
        }
        return result;
    }
}
