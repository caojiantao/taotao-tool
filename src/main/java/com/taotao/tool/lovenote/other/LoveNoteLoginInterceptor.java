package com.taotao.tool.lovenote.other;

import com.taotao.tool.lovenote.service.ILoveNoteUserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        String token = request.getHeader("token");
        String openid = request.getHeader("openid");
//        User user = userService.verifyToken(token, openid);
        LoveNoteLoginApi annotation = handlerMethod.getMethod().getAnnotation(LoveNoteLoginApi.class);
//        // 请求接口需要登录，且用户未登录是，重定向到指定登录页
//        if (Objects.nonNull(annotation) && Objects.isNull(user)) {
//            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//            ApiException exception = new ApiException(ApiCode.NOT_LOGIN, "未登录");
//            ApiResp<Void> resp = ApiResp.fail(exception, null);
//            String respJson = JsonUtils.toJson(resp);
//            response.getWriter().write(respJson);
//            return false;
//        }
        return true;
    }
}
