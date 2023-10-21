package com.taotao.tool.lovenote.service.impl;

import com.taotao.tool.admin.service.IDictionaryService;
import com.taotao.tool.lovenote.model.LoveNoteTrendMedia;
import com.taotao.tool.lovenote.mapper.LoveNoteTrendMediaMapper;
import com.taotao.tool.lovenote.service.ILoveNoteTrendMediaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
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
}
