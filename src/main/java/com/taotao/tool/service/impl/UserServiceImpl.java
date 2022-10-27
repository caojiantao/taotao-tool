package com.taotao.tool.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taotao.tool.mapper.UserMapper;
import com.taotao.tool.model.User;
import com.taotao.tool.service.IUserService;
import com.taotao.tool.util.DigestUtils;
import com.taotao.tool.yml.LoginYml;
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
        return sign(password, loginYml.getPasswordSalt());
    }

    @Override
    public String getToken(Integer userId) {
        return sign(userId.toString(), loginYml.getTokenSalt());
    }

    @Override
    public User verifyToken(String token, Integer userId) {
        if (!StringUtils.hasLength(token) || Objects.isNull(userId)) {
            return null;
        }
        String sign = sign(userId.toString(), loginYml.getTokenSalt());
        if (!Objects.equals(sign, token)) {
            log.error("不合法的签名");
            return null;
        }
        return getById(userId);
    }

    private String sign(Object payload, String salt) {
        return DigestUtils.md5(payload + salt);
    }
}
