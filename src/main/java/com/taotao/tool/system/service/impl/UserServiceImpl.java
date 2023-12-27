package com.taotao.tool.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taotao.tool.system.mapper.UserMapper;
import com.taotao.tool.system.model.User;
import com.taotao.tool.system.service.IUserService;
import com.taotao.tool.common.util.DigestUtils;
import com.taotao.tool.spring.yml.LoginYml;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author taotao
 * @since 2022-10-25
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private LoginYml loginYml;

    @Override
    public String encryptPassword(String password) {
        return DigestUtils.md5(password + loginYml.getPasswordSalt());
    }

    @Override
    public String getToken(Integer userId) {
        return DigestUtils.md5(userId.toString() + loginYml.getTokenSalt());
    }

    @Override
    public User verifyToken(String token, Integer userId) {
        if (!StringUtils.hasLength(token) || Objects.isNull(userId)) {
            return null;
        }
        String signToken = getToken(userId);
        if (!Objects.equals(signToken, token)) {
            // 签名不合法
            return null;
        }
        return getById(userId);
    }
}
