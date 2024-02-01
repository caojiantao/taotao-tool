package com.taotao.tool.system.interceptor;

import com.taotao.tool.common.constants.EApiCode;
import com.taotao.tool.common.dto.ApiResult;
import com.taotao.tool.common.util.JsonUtils;
import com.taotao.tool.system.annotation.RequireLogin;
import com.taotao.tool.system.model.SystemUser;
import com.taotao.tool.system.service.ISystemUserService;
import com.taotao.tool.system.util.LoginUtils;
import com.taotao.tool.system.yml.LoginYml;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.Optional;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private ISystemUserService userService;
    @Autowired
    private LoginYml loginYml;

    @Override
    public boolean preHandle(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull Object handler
    ) throws Exception {
        boolean flag = handler instanceof HandlerMethod;
        if (!flag) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Cookie tokenCookie = WebUtils.getCookie(request, "token");
        Cookie userIdCookie = WebUtils.getCookie(request, "user_id");
        String token = Optional.ofNullable(tokenCookie).map(Cookie::getValue).orElse(null);
        Integer userId = Optional.ofNullable(userIdCookie).map(Cookie::getValue).map(Integer::parseInt).orElse(null);
        SystemUser user = userService.verifyToken(token, userId);
        LoginUtils.setCurrentUser(user);
        RequireLogin annotation = handlerMethod.getMethod().getAnnotation(RequireLogin.class);
        // 请求接口需要登录，且用户未登录是，重定向到指定登录页
        if (Objects.nonNull(annotation) && Objects.isNull(user)) {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            ApiResult<Void> resp = ApiResult.build(EApiCode.NOT_LOGIN);
            String respJson = JsonUtils.toJson(resp);
            response.getWriter().write(respJson);
            return false;
        }
        return true;
    }

    @Override
    public void afterCompletion(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull Object handler,
            Exception ex
    ) throws Exception {
        LoginUtils.clearCurrentUser();
    }
}
