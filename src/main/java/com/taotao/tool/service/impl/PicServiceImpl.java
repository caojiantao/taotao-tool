package com.taotao.tool.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taotao.tool.mapper.PicMapper;
import com.taotao.tool.model.Pic;
import com.taotao.tool.service.IPicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author taotao
 * @since 2022-08-22
 */
@Slf4j
@Service
public class PicServiceImpl extends ServiceImpl<PicMapper, Pic> implements IPicService {

    @Override
    public Pic getByMd5(String md5) {
        return query()
                .eq("md5", md5)
                .one();
    }
}
