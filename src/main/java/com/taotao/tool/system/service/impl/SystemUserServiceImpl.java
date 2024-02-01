package com.taotao.tool.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taotao.tool.common.util.DigestUtils;
import com.taotao.tool.system.mapper.SystemUserMapper;
import com.taotao.tool.system.model.SystemUser;
import com.taotao.tool.system.service.ISystemUserService;
import com.taotao.tool.system.yml.LoginYml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author caojiantao
 * @since 2024-02-01
 */
@Service
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser> implements ISystemUserService {

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
    public SystemUser verifyToken(String token, Integer userId) {
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
