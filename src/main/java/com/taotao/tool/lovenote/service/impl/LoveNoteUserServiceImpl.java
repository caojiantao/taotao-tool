package com.taotao.tool.lovenote.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.taotao.tool.dto.resp.LoveNoteLoginResp;
import com.taotao.tool.lovenote.mapper.LoveNoteUserMapper;
import com.taotao.tool.lovenote.model.LoveNoteUser;
import com.taotao.tool.lovenote.service.ILoveNoteUserService;
import com.taotao.tool.util.DigestUtils;
import com.taotao.tool.util.JsonUtils;
import com.taotao.tool.yml.LoveNoteYml;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.text.MessageFormat;
import java.time.Duration;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author taotao
 * @since 2023-10-11
 */
@Service
public class LoveNoteUserServiceImpl extends ServiceImpl<LoveNoteUserMapper, LoveNoteUser> implements ILoveNoteUserService {


    @Autowired
    private LoveNoteYml loveNoteYml;

    @Override
    public LoveNoteUser getUserByOpenid(String openid) {
        return query().eq("openid", openid).one();
    }

    @Override
    public LoveNoteLoginResp login(String code) {
        String openid = getOpenidByCode(code);
        String token = getToken(openid);
        LoveNoteUser user = getUserByOpenid(openid);
        LoveNoteLoginResp resp = new LoveNoteLoginResp();
        resp.setOpenid(openid);
        resp.setUser(user);
        resp.setToken(token);
        return resp;
    }

    @Override
    public LoveNoteLoginResp register(LoveNoteUser user) {
        save(user);
        LoveNoteLoginResp resp = new LoveNoteLoginResp();
        resp.setOpenid(user.getOpenid());
        resp.setUser(user);
        String token = getToken(user.getOpenid());
        resp.setToken(token);
        return resp;
    }

    @Override
    public String getToken(String openid) {
        return DigestUtils.md5(openid + loveNoteYml.getTokenSalt());
    }

    @Override
    public LoveNoteUser verifyToken(String token, String openid) {
        if (!StringUtils.hasLength(token) || Objects.isNull(openid)) {
            return null;
        }
        String signToken = getToken(openid);
        if (!Objects.equals(signToken, token)) {
            // 签名不合法
            return null;
        }
        return getUserByOpenid(openid);
    }

    @SneakyThrows
    private String getOpenidByCode(String code) {
        String urlHolder = "https://api.weixin.qq.com/sns/jscode2session?appid={0}&secret={1}&js_code={2}&grant_type=authorization_code";
        String url = MessageFormat.format(urlHolder, loveNoteYml.getAppId(), loveNoteYml.getAppSecret(), code);
        Mono<String> mono = WebClient.create()
                .method(HttpMethod.GET)
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .timeout(Duration.ofSeconds(2));
        String json = mono.block();
        JsonNode jsonNode = JsonUtils.getMapper().readTree(json);
        return jsonNode.get("openid").textValue();
    }
}