package com.taotao.tool.lovenote.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.taotao.tool.admin.dto.resp.LoveNoteLoginResp;
import com.taotao.tool.admin.service.WorkWxService;
import com.taotao.tool.common.util.ApiAssertUtils;
import com.taotao.tool.common.util.DigestUtils;
import com.taotao.tool.common.util.JsonUtils;
import com.taotao.tool.lovenote.entity.LoveNoteUserRegisterRequest;
import com.taotao.tool.lovenote.mapper.LoveNoteUserMapper;
import com.taotao.tool.lovenote.model.LoveNoteUser;
import com.taotao.tool.lovenote.service.ILoveNoteTrendMediaService;
import com.taotao.tool.lovenote.service.ILoveNoteUserService;
import com.taotao.tool.spring.yml.LoveNoteYml;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.text.MessageFormat;
import java.time.Duration;
import java.util.Objects;
import java.util.Properties;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author taotao
 * @since 2023-10-11
 */
@Slf4j
@Service
public class LoveNoteUserServiceImpl extends ServiceImpl<LoveNoteUserMapper, LoveNoteUser> implements ILoveNoteUserService {

    @Autowired
    private WorkWxService workWxService;

    @Autowired
    private LoveNoteYml loveNoteYml;

    @Autowired
    private ILoveNoteTrendMediaService mediaService;

    @Override
    public LoveNoteUser getUserByOpenid(String openid) {
        LoveNoteUser user = query().eq("openid", openid).one();
        if (Objects.nonNull(user)) {
            String mediaUrl = mediaService.getMediaUrl(user.getAvatarUrl());
            user.setAvatarUrl(mediaUrl);
        }
        return user;
    }

    @Override
    public LoveNoteLoginResp login(String code) {
        LoveNoteLoginResp resp = new LoveNoteLoginResp();
        String openid = getOpenidByCode(code);
        resp.setOpenid(openid);
        LoveNoteUser user = getUserByOpenid(openid);
        log.info("act=LoveNoteUserServiceImpl.login openid={} user={}", openid, JsonUtils.toJson(user));
        if (Objects.isNull(user)) {
            // 用户未注册
            return null;
        }
        String token = getToken(openid);
        resp.setToken(token);
        return resp;
    }

    @Override
    public LoveNoteLoginResp register(LoveNoteUserRegisterRequest request) {
        String openid = getOpenidByCode(request.getCode());
        LoveNoteUser currentUser = getUserByOpenid(openid);
        ApiAssertUtils.isNull(currentUser, "该 openid 已注册");
        log.info("act=LoveNoteUserServiceImpl.register request={}", JsonUtils.toJson(request));
        LoveNoteUser user = new LoveNoteUser();
        user.setOpenid(openid);
        String mediaFileName = mediaService.getMediaFileName(request.getAvatarUrl());
        user.setAvatarUrl(mediaFileName);
        user.setNickname(request.getNickname());
        user.setGender(request.getGender());
        save(user);
        LoveNoteLoginResp resp = new LoveNoteLoginResp();
        resp.setOpenid(user.getOpenid());
        String token = getToken(user.getOpenid());
        resp.setToken(token);
        sendWxNotice(user);
        return resp;
    }

    private void sendWxNotice(LoveNoteUser user) {
        Properties properties = new Properties();
        properties.setProperty("openid", user.getOpenid());
        properties.setProperty("avatarUrl", mediaService.getMediaUrl(user.getAvatarUrl()));
        properties.setProperty("nickname", user.getNickname());
        properties.setProperty("gender", user.getGender().toString());
        workWxService.sendMessage("love_note_notice_register", properties);
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
