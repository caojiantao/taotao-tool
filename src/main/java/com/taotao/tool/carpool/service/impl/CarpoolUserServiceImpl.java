package com.taotao.tool.carpool.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.taotao.tool.admin.service.WorkWxService;
import com.taotao.tool.carpool.entity.CarpoolLoginResp;
import com.taotao.tool.carpool.entity.CarpoolUserRegisterReq;
import com.taotao.tool.carpool.mapper.CarpoolUserMapper;
import com.taotao.tool.carpool.model.CarpoolUser;
import com.taotao.tool.carpool.service.ICarpoolMediaService;
import com.taotao.tool.carpool.service.ICarpoolUserService;
import com.taotao.tool.common.util.ApiAssertUtils;
import com.taotao.tool.common.util.DigestUtils;
import com.taotao.tool.common.util.JsonUtils;
import com.taotao.tool.spring.yml.CarpoolYml;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.text.MessageFormat;
import java.time.Duration;
import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author caojiantao
 * @since 2023-12-21
 */
@Slf4j
@Service
public class CarpoolUserServiceImpl extends ServiceImpl<CarpoolUserMapper, CarpoolUser> implements ICarpoolUserService {

    @Autowired
    private WorkWxService workWxService;

    @Autowired
    private CarpoolYml carpoolYml;

    @Autowired
    private ICarpoolMediaService mediaService;

    @Override
    public CarpoolUser getUserByOpenid(String openid) {
        CarpoolUser user = query().eq("openid", openid).one();
        if (Objects.nonNull(user)) {
            String mediaUrl = mediaService.getMediaUrl(user.getAvatar());
            user.setAvatar(mediaUrl);
        }
        return user;
    }

    @Override
    public Map<String, CarpoolUser> getUserMapByOpenidList(List<String> openidList) {
        Map<String, CarpoolUser> map = new HashMap<>();
        if (CollectionUtils.isEmpty(openidList)) {
            return map;
        }
        List<CarpoolUser> list = query().in("openid", openidList).list();
        for (CarpoolUser item : list) {
            String mediaUrl = mediaService.getMediaUrl(item.getAvatar());
            item.setAvatar(mediaUrl);
            map.put(item.getOpenid(), item);
        }
        return map;
    }

    @Override
    public CarpoolLoginResp login(String code) {
        CarpoolLoginResp resp = new CarpoolLoginResp();
        String openid = getOpenidByCode(code);
        resp.setOpenid(openid);
        CarpoolUser user = getUserByOpenid(openid);
        log.info("act=CarpoolUserServiceImpl.login openid={} user={}", openid, JsonUtils.toJson(user));
        if (Objects.isNull(user)) {
            // 用户未注册
            return null;
        }
        String token = getToken(openid);
        resp.setToken(token);
        return resp;
    }

    @Override
    public CarpoolLoginResp register(CarpoolUserRegisterReq request) {
        String openid = getOpenidByCode(request.getCode());
        CarpoolUser currentUser = getUserByOpenid(openid);
        ApiAssertUtils.isNull(currentUser, "该 openid 已注册");
        log.info("act=CarpoolUserServiceImpl.register request={}", JsonUtils.toJson(request));
        CarpoolUser user = new CarpoolUser();
        user.setOpenid(openid);
        String mediaFileName = mediaService.getMediaFileName(request.getAvatar());
        user.setAvatar(mediaFileName);
        user.setNickname(request.getNickname());
        save(user);
        CarpoolLoginResp resp = new CarpoolLoginResp();
        resp.setOpenid(user.getOpenid());
        String token = getToken(user.getOpenid());
        resp.setToken(token);
        sendWxNotice(user);
        return resp;
    }

    private void sendWxNotice(CarpoolUser user) {
        Properties properties = new Properties();
        properties.setProperty("openid", user.getOpenid());
        properties.setProperty("nickname", user.getNickname());
        workWxService.sendMessage("carpool_notice_register", properties);
    }

    @Override
    public String getToken(String openid) {
        return DigestUtils.md5(openid + carpoolYml.getTokenSalt());
    }

    @Override
    public CarpoolUser verifyToken(String token, String openid) {
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
        String url = MessageFormat.format(urlHolder, carpoolYml.getAppId(), carpoolYml.getAppSecret(), code);
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
