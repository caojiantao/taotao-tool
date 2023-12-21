package com.taotao.tool.carpool.service.impl;

import com.taotao.tool.admin.service.IDictionaryService;
import com.taotao.tool.carpool.model.CarpoolMedia;
import com.taotao.tool.carpool.mapper.CarpoolMediaMapper;
import com.taotao.tool.carpool.service.ICarpoolMediaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taotao.tool.common.util.ApiAssertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author caojiantao
 * @since 2023-12-21
 */
@Service
public class CarpoolMediaServiceImpl extends ServiceImpl<CarpoolMediaMapper, CarpoolMedia> implements ICarpoolMediaService {

    @Autowired
    private IDictionaryService dictionaryService;

    @Override
    public String getMediaUrl(String fileName) {
        String imageDomain = dictionaryService.getValueByKey("image_domain", String.class);
        return imageDomain + "/carpool/" + fileName;
    }

    @Override
    public String getMediaFileName(String mediaUrl) {
        String imageDomain = dictionaryService.getValueByKey("image_domain", String.class);
        ApiAssertUtils.isTrue(mediaUrl.startsWith(imageDomain), "资源路径不合法");
        return mediaUrl.substring(mediaUrl.lastIndexOf("/") + 1);
    }
}
