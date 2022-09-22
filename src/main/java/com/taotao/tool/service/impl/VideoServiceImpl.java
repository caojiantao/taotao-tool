package com.taotao.tool.service.impl;

import com.taotao.tool.model.Video;
import com.taotao.tool.mapper.VideoMapper;
import com.taotao.tool.service.IVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author taotao
 * @since 2022-09-22
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements IVideoService {

}
