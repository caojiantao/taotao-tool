package com.taotao.tool.carpool.service;

import com.taotao.tool.carpool.model.CarpoolLine;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author caojiantao
 * @since 2023-12-21
 */
public interface ICarpoolLineService extends IService<CarpoolLine> {

    CarpoolLine getLineByOpenid(String openid);

    void updateLineByOpenid(CarpoolLine line);
}
