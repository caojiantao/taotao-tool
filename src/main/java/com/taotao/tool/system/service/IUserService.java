package com.taotao.tool.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taotao.tool.system.model.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author taotao
 * @since 2022-10-25
 */
public interface IUserService extends IService<User> {

    String encryptPassword(String password);

    String getToken(Integer userId);

    User verifyToken(String token, Integer userId);
}
