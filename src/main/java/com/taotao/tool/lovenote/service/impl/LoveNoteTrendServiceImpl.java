package com.taotao.tool.lovenote.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taotao.tool.system.service.IDictionaryService;
import com.taotao.tool.system.service.WorkWxService;
import com.taotao.tool.lovenote.entity.LoveNoteTrendDto;
import com.taotao.tool.lovenote.entity.LoveNoteTrendMediaDto;
import com.taotao.tool.lovenote.entity.LoveNoteTrendQuery;
import com.taotao.tool.lovenote.entity.LoveNoteTrendVo;
import com.taotao.tool.lovenote.mapper.LoveNoteTrendMapper;
import com.taotao.tool.lovenote.model.LoveNoteCp;
import com.taotao.tool.lovenote.model.LoveNoteTrend;
import com.taotao.tool.lovenote.model.LoveNoteTrendMedia;
import com.taotao.tool.lovenote.model.LoveNoteUser;
import com.taotao.tool.lovenote.other.LoveNoteLoginUtils;
import com.taotao.tool.lovenote.service.ILoveNoteCpService;
import com.taotao.tool.lovenote.service.ILoveNoteTrendMediaService;
import com.taotao.tool.lovenote.service.ILoveNoteTrendService;
import com.taotao.tool.lovenote.service.ILoveNoteUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
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
    @Autowired
    private WorkWxService workWxService;
    @Autowired
    private IDictionaryService dictionaryService;

    @Override
    @Transactional
    public void addTrend(LoveNoteTrendDto trendDto) {
        String openid = LoveNoteLoginUtils.getCurrentUser().getOpenid();
        LoveNoteCp cp = cpService.getCpByOpenid(openid);
        LoveNoteTrend trend = new LoveNoteTrend();
        trend.setCpId(cp.getId());
        trend.setOpenid(openid);
        trend.setContent(trendDto.getContent());
        save(trend);
        if (CollectionUtils.isEmpty(trendDto.getMediaList())) {
            return;
        }
        List<LoveNoteTrendMedia> mediaList = new ArrayList<>();
        for (LoveNoteTrendMediaDto mediaDto : trendDto.getMediaList()) {
            LoveNoteTrendMedia media = mediaService.covertMediaDtoToMedia(trend, mediaDto);
            mediaList.add(media);
        }
        mediaService.saveBatch(mediaList);
        sendWxNotice(trendDto, cp);
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
        Map<Integer, List<LoveNoteTrendMedia>> mediaMap = mediaService.query().in("trend_id", trendIdList).list()
                .stream().collect(Collectors.groupingBy(LoveNoteTrendMedia::getTrendId));
        List<LoveNoteTrendVo> list = new ArrayList<>();
        for (LoveNoteTrend record : records) {
            LoveNoteTrendVo dto = buildLoveNoteTrendDto(record, mediaMap.getOrDefault(record.getId(), new ArrayList<>()));
            list.add(dto);
        }
        return list;
    }

    private LoveNoteTrendVo buildLoveNoteTrendDto(LoveNoteTrend record, List<LoveNoteTrendMedia> mediaList) {
        LoveNoteTrendVo vo = new LoveNoteTrendVo();
        BeanUtils.copyProperties(record, vo);
        String timeFmt = timeFmt(record.getCreateTime());
        vo.setTimeFmt(timeFmt);
        LoveNoteCp cp = cpService.getCpByOpenid(record.getOpenid());
        vo.setCp(cp);
        LoveNoteUser user = userService.getUserByOpenid(record.getOpenid());
        LoveNoteTrendVo.UserVo userVo = new LoveNoteTrendVo.UserVo();
        userVo.setNickname(user.getNickname());
        userVo.setAvatarUrl(user.getAvatarUrl());
        vo.setUser(userVo);
        String partnerOpenid = Objects.equals(cp.getInviter(), record.getOpenid()) ? cp.getInvitee() : cp.getInviter();
        LoveNoteUser partner = userService.getUserByOpenid(partnerOpenid);
        LoveNoteTrendVo.UserVo partnerVo = new LoveNoteTrendVo.UserVo();
        partnerVo.setNickname(partner.getNickname());
        partnerVo.setAvatarUrl(mediaService.getMediaUrl(partner.getAvatarUrl()));
        vo.setPartner(partnerVo);

        List<LoveNoteTrendMediaDto> mediaDtoList = new ArrayList<>();
        vo.setMediaList(mediaDtoList);
        for (LoveNoteTrendMedia media : mediaList) {
            LoveNoteTrendMediaDto mediaDto = mediaService.covertMediaModelToMediaDto(media);
            mediaDtoList.add(mediaDto);
        }
        return vo;
    }

    private String timeFmt(LocalDateTime createTime) {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(createTime, now);
        if (duration.toDays() > 0) {
            return duration.toDays() + "天前";
        } else if (duration.toHours() > 0) {
            return duration.toHours() + "小时前";
        } else if (duration.toMinutes() > 0) {
            return duration.toMinutes() + "分钟前";
        } else {
            return "刚刚";
        }
    }

    private void sendWxNotice(LoveNoteTrendDto trendDto, LoveNoteCp cp) {
        Properties properties = new Properties();
        LoveNoteUser currentUser = LoveNoteLoginUtils.getCurrentUser();
        properties.setProperty("nickname", currentUser.getNickname());
        properties.setProperty("cpName", cp.getCpName());
        properties.setProperty("content", trendDto.getContent());
        String mediaList = trendDto.getMediaList().stream()
                .map(LoveNoteTrendMediaDto::getImage)
                .map(LoveNoteTrendMediaDto.Image::getUrl)
                .map(url -> "[图片](" + mediaService.getMediaUrl(url) + ")")
                .collect(Collectors.joining(","));
        properties.setProperty("mediaList", mediaList);
        workWxService.sendMessage("love_note_notice_addtrend", properties);
    }
}
