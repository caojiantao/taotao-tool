package com.taotao.tool.lovenote.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taotao.tool.lovenote.entity.LoveNoteTrendMediaDto;
import com.taotao.tool.lovenote.model.LoveNoteTrend;
import com.taotao.tool.lovenote.model.LoveNoteTrendMedia;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author taotao
 * @since 2023-10-11
 */
public interface ILoveNoteTrendMediaService extends IService<LoveNoteTrendMedia> {

    String getMediaUrl(String fileName);

    String getMediaFileName(String mediaUrl);

    LoveNoteTrendMedia covertMediaDtoToMedia(LoveNoteTrend trend, LoveNoteTrendMediaDto mediaDto);

    LoveNoteTrendMediaDto covertMediaModelToMediaDto(LoveNoteTrendMedia media);
}
