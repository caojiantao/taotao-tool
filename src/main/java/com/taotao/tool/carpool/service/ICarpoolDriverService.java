package com.taotao.tool.carpool.service;

import com.taotao.tool.carpool.model.CarpoolDriver;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author caojiantao
 * @since 2023-12-21
 */
public interface ICarpoolDriverService extends IService<CarpoolDriver> {

    CarpoolDriver getDriverByOpenid(String openid);

    Map<String, CarpoolDriver> getDriverMapByOpenidList(List<String> openidList);
}
