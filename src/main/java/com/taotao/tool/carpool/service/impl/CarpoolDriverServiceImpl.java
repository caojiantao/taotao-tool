package com.taotao.tool.carpool.service.impl;

import com.taotao.tool.carpool.model.CarpoolDriver;
import com.taotao.tool.carpool.mapper.CarpoolDriverMapper;
import com.taotao.tool.carpool.service.ICarpoolDriverService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class CarpoolDriverServiceImpl extends ServiceImpl<CarpoolDriverMapper, CarpoolDriver> implements ICarpoolDriverService {

    @Override
    public CarpoolDriver getDriverByOpenid(String openid) {
        return query().eq("openid", openid).one();
    }
}
