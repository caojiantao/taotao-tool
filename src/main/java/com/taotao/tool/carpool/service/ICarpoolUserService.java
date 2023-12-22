package com.taotao.tool.carpool.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taotao.tool.carpool.entity.CarpoolLoginResp;
import com.taotao.tool.carpool.entity.CarpoolUserRegisterReq;
import com.taotao.tool.carpool.model.CarpoolUser;

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
public interface ICarpoolUserService extends IService<CarpoolUser> {

    CarpoolUser getUserByOpenid(String openid);

    Map<String, CarpoolUser> getUserMapByOpenidList(List<String> openidList);

    CarpoolLoginResp login(String code);

    CarpoolLoginResp register(CarpoolUserRegisterReq request);

    String getToken(String openid);

    CarpoolUser verifyToken(String token, String openid);
}
