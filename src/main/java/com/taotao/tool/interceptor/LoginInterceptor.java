package com.taotao.tool.interceptor;

import com.taotao.tool.annotation.RequireLogin;
import com.taotao.tool.dto.resp.ApiResp;
import com.taotao.tool.enums.EApiCode;
import com.taotao.tool.exception.ApiException;
import com.taotao.tool.model.User;
import com.taotao.tool.service.IUserService;
import com.taotao.tool.util.JsonUtils;
import com.taotao.tool.util.LoginUtils;
import com.taotao.tool.yml.LoginYml;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private IUserService userService;
    @Autowired
    private LoginYml loginYml;

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request,
                             @NotNull HttpServletResponse response,
                             @NotNull Object handler) throws Exception {
        boolean isHandlerMethod = handler instanceof HandlerMethod;
        if (!isHandlerMethod) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        RequireLogin annotation = handlerMethod.getMethod().getAnnotation(RequireLogin.class);
        if (Objects.nonNull(annotation)) {
            String token = request.getHeader("x-token");
            User user = userService.parseToken(token);
            if (Objects.isNull(user)) {
                response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
                Map<String, Object> data = new HashMap<>();
                data.put("redirectUrl", loginYml.getRedirectUrl());
                ApiResp<Map<String, Object>> resp = ApiResp.fail(new ApiException(EApiCode.NOT_LOGIN), data);
                String respJson = JsonUtils.toJson(resp);
                response.getWriter().write(respJson);
                return false;
            }
            LoginUtils.setCurrentUser(user);
        }
        return true;
    }
}
