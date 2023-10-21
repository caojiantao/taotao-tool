package com.taotao.tool.lovenote.service;

import com.taotao.tool.lovenote.model.LoveNoteTrendMedia;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author taotao
 * @since 2023-10-11
 */
public interface ILoveNoteTrendMediaService extends IService<LoveNoteTrendMedia> {

    String getMediaUrl(String fileName);
}
