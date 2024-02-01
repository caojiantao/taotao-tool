package com.taotao.tool.system.service;

import com.taotao.tool.system.model.SystemUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author caojiantao
 * @since 2024-02-01
 */
public interface ISystemUserService extends IService<SystemUser> {

    String encryptPassword(String password);

    String getToken(Integer userId);

    SystemUser verifyToken(String token, Integer userId);

}
