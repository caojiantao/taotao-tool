package com.taotao.tool.lovenote.other;

import com.taotao.tool.common.constants.EApiCode;
import com.taotao.tool.common.constants.IApiCode;
import com.taotao.tool.common.dto.ApiResult;
import com.taotao.tool.common.exception.ApiException;
import com.taotao.tool.common.util.JsonUtils;
import com.taotao.tool.common.util.LoginUtils;
import com.taotao.tool.lovenote.model.LoveNoteUser;
import com.taotao.tool.lovenote.service.ILoveNoteUserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Component
public class LoveNoteLoginInterceptor implements HandlerInterceptor {

    @Autowired
    private ILoveNoteUserService userService;

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
        LoveNoteLoginApi annotation = handlerMethod.getMethod().getAnnotation(LoveNoteLoginApi.class);
        if (Objects.isNull(annotation)) {
            return true;
        }
        String token = request.getHeader("token");
        String openid = request.getHeader("openid");
        LoveNoteUser user = userService.verifyToken(token, openid);
        // 请求接口需要登录，且用户未登录需要拦截
        if (Objects.isNull(user)) {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            ApiResult<Void> resp = ApiResult.build(EApiCode.NOT_LOGIN);
            String respJson = JsonUtils.toJson(resp);
            response.getWriter().write(respJson);
            return false;
        }
        LoveNoteLoginUtils.setCurrentUser(user);
        return true;
    }



    @Override
    public void afterCompletion(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull Object handler,
            Exception ex
    ) throws Exception {
        LoveNoteLoginUtils.clearCurrentUser();
    }
}
