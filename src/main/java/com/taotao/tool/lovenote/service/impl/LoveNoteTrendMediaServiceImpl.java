package com.taotao.tool.lovenote.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taotao.tool.admin.service.IDictionaryService;
import com.taotao.tool.common.util.ApiAssertUtils;
import com.taotao.tool.common.util.JsonUtils;
import com.taotao.tool.lovenote.constant.ELoveNoteTrendMediaType;
import com.taotao.tool.lovenote.entity.LoveNoteTrendMediaDto;
import com.taotao.tool.lovenote.mapper.LoveNoteTrendMediaMapper;
import com.taotao.tool.lovenote.model.LoveNoteTrend;
import com.taotao.tool.lovenote.model.LoveNoteTrendMedia;
import com.taotao.tool.lovenote.service.ILoveNoteTrendMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author taotao
 * @since 2023-10-11
 */
@Service
public class LoveNoteTrendMediaServiceImpl extends ServiceImpl<LoveNoteTrendMediaMapper, LoveNoteTrendMedia> implements ILoveNoteTrendMediaService {

    @Autowired
    private IDictionaryService dictionaryService;

    @Override
    public String getMediaUrl(String fileName) {
        String imageDomain = dictionaryService.getValueByKey("image_domain", String.class);
        return imageDomain + "/love-note/" + fileName;
    }

    @Override
    public String getMediaFileName(String mediaUrl) {
        String imageDomain = dictionaryService.getValueByKey("image_domain", String.class);
        ApiAssertUtils.isTrue(mediaUrl.startsWith(imageDomain), "资源路径不合法");
        return mediaUrl.substring(mediaUrl.lastIndexOf("/") + 1);
    }

    @Override
    public LoveNoteTrendMedia covertMediaDtoToMedia(LoveNoteTrend trend, LoveNoteTrendMediaDto mediaDto) {
        LoveNoteTrendMedia media = new LoveNoteTrendMedia();
        media.setTrendId(trend.getId());
        media.setCpId(trend.getCpId());
        media.setOpenid(trend.getOpenid());
        media.setType(mediaDto.getType().name());

        String content = null;
        if (ELoveNoteTrendMediaType.IMAGE.equals(mediaDto.getType())) {
            LoveNoteTrendMediaDto.Image image = mediaDto.getImage();
            String mediaFileName = getMediaFileName(image.getUrl());
            image.setUrl(mediaFileName);
            content = JsonUtils.toJson(image);
        }
        media.setContent(content);

        return media;
    }

    @Override
    public LoveNoteTrendMediaDto covertMediaModelToMediaDto(LoveNoteTrendMedia media) {
        LoveNoteTrendMediaDto mediaDto = new LoveNoteTrendMediaDto();
        mediaDto.setType(ELoveNoteTrendMediaType.valueOf(media.getType()));
        if (ELoveNoteTrendMediaType.IMAGE.equals(mediaDto.getType())) {
            LoveNoteTrendMediaDto.Image image = JsonUtils.parse(media.getContent(), LoveNoteTrendMediaDto.Image.class);
            String mediaUrl = getMediaUrl(image.getUrl());
            image.setUrl(mediaUrl);
            mediaDto.setImage(image);
        }
        return mediaDto;
    }
}
