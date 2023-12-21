package com.taotao.tool.carpool.service;

import com.taotao.tool.carpool.model.CarpoolMedia;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author caojiantao
 * @since 2023-12-21
 */
public interface ICarpoolMediaService extends IService<CarpoolMedia> {

    String getMediaUrl(String fileName);

    String getMediaFileName(String mediaUrl);
}
