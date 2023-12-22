package com.taotao.tool.carpool.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taotao.tool.carpool.mapper.CarpoolLineMapper;
import com.taotao.tool.carpool.model.CarpoolLine;
import com.taotao.tool.carpool.service.ICarpoolLineService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author caojiantao
 * @since 2023-12-21
 */
@Service
public class CarpoolLineServiceImpl extends ServiceImpl<CarpoolLineMapper, CarpoolLine> implements ICarpoolLineService {

    @Override
    public CarpoolLine getLineByOpenid(String openid) {
        return query().eq("openid", openid).one();
    }

    @Override
    public void updateLineByOpenid(CarpoolLine line) {
        update().eq("openid", line.getOpenid()).update(line);
    }
}
