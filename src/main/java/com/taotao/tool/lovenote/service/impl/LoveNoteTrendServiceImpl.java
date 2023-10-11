package com.taotao.tool.lovenote.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taotao.tool.lovenote.entity.LoveNoteTrendQuery;
import com.taotao.tool.lovenote.entity.LoveNoteTrendDto;
import com.taotao.tool.lovenote.entity.LoveNoteTrendMediaDto;
import com.taotao.tool.lovenote.entity.LoveNoteTrendVo;
import com.taotao.tool.lovenote.mapper.LoveNoteTrendMapper;
import com.taotao.tool.lovenote.model.LoveNoteCp;
import com.taotao.tool.lovenote.model.LoveNoteTrend;
import com.taotao.tool.lovenote.model.LoveNoteTrendMedia;
import com.taotao.tool.lovenote.model.LoveNoteUser;
import com.taotao.tool.lovenote.service.ILoveNoteCpService;
import com.taotao.tool.lovenote.service.ILoveNoteTrendMediaService;
import com.taotao.tool.lovenote.service.ILoveNoteTrendService;
import com.taotao.tool.lovenote.service.ILoveNoteUserService;
import com.taotao.tool.util.JsonUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author taotao
 * @since 2023-10-11
 */
@Service
public class LoveNoteTrendServiceImpl extends ServiceImpl<LoveNoteTrendMapper, LoveNoteTrend> implements ILoveNoteTrendService {

    @Autowired
    private ILoveNoteUserService userService;
    @Autowired
    private ILoveNoteCpService cpService;
    @Autowired
    private ILoveNoteTrendMediaService mediaService;

    @Override
    @Transactional
    public void addTrend(LoveNoteTrendDto trendDto) {
        LoveNoteCp cp = cpService.getCpByOpenid(trendDto.getOpenid());
        LoveNoteTrend trend = JsonUtils.convert(trendDto, LoveNoteTrend.class);
        trend.setCpId(cp.getId());
        save(trend);
        if (CollectionUtils.isEmpty(trendDto.getMediaList())) {
            return;
        }
        List<LoveNoteTrendMedia> mediaList = new ArrayList<>();
        for (LoveNoteTrendMediaDto mediaDto : trendDto.getMediaList()) {
            LoveNoteTrendMedia media = new LoveNoteTrendMedia();
            media.setTrendId(trend.getId());
            media.setCpId(cp.getId());
            media.setOpenid(trend.getOpenid());
            media.setType(mediaDto.getType().name());
            media.setContent(mediaDto.toContent());
            mediaList.add(media);
        }
        mediaService.saveBatch(mediaList);
    }

    @Override
    @Transactional
    public void removeTrendById(Integer id) {
        cpService.update().eq("trend_id", id).remove();
        removeById(id);
    }

    @Override
    public List<LoveNoteTrendVo> getLoveNoteTrendList(LoveNoteTrendQuery query) {
        IPage<LoveNoteTrend> page = new Page<>(query.getPage(), query.getSize());
        query().eq(Objects.nonNull(query.getCpId()), "cp_id", query.getCpId())
                .eq(Objects.nonNull(query.getOpenid()), "openid", query.getOpenid())
                .orderByDesc("create_time")
                .page(page);
        List<LoveNoteTrend> records = page.getRecords();
        if (CollectionUtils.isEmpty(records)) {
            return new ArrayList<>();
        }
        List<Integer> trendIdList = records.stream().map(LoveNoteTrend::getId).collect(Collectors.toList());
        Map<Integer, LoveNoteTrendMedia> mediaMap = mediaService.query().in("trend_id", trendIdList).list()
                .stream().collect(Collectors.toMap(LoveNoteTrendMedia::getTrendId, Function.identity()));
        List<LoveNoteTrendVo> list = new ArrayList<>();
        for (LoveNoteTrend record : records) {
            LoveNoteTrendVo dto = buildLoveNoteTrendDto(record, mediaMap.get(record.getId()));
            list.add(dto);
        }
        return list;
    }

    private LoveNoteTrendVo buildLoveNoteTrendDto(LoveNoteTrend record, LoveNoteTrendMedia loveNoteTrendMedia) {
        LoveNoteTrendVo vo = new LoveNoteTrendVo();
        BeanUtils.copyProperties(record, vo);
        LoveNoteCp cp = cpService.getCpByOpenid(record.getOpenid());
        vo.setCp(cp);
        LoveNoteUser user = userService.getUserByOpenid(record.getOpenid());
        vo.setUser(user);
        String partnerOpenid = Objects.equals(cp.getInviter(), record.getOpenid()) ? cp.getInvitee() : cp.getInviter();
        LoveNoteUser partner = userService.getUserByOpenid(partnerOpenid);
        vo.setPartner(partner);
        return vo;
    }
}
