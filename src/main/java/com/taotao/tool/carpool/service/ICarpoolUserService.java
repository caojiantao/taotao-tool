package com.taotao.tool.carpool.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taotao.tool.carpool.entity.CarpoolLoginResp;
import com.taotao.tool.carpool.entity.CarpoolUserRegisterRequest;
import com.taotao.tool.carpool.model.CarpoolUser;

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

    CarpoolLoginResp login(String code);

    CarpoolLoginResp register(CarpoolUserRegisterRequest request);

    String getToken(String openid);

    CarpoolUser verifyToken(String token, String openid);
}
