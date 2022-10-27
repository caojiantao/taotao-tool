package com.taotao.tool.controller;

import com.taotao.tool.dto.req.LoginReq;
import com.taotao.tool.dto.resp.ApiResp;
import com.taotao.tool.dto.resp.LoginResp;
import com.taotao.tool.model.User;
import com.taotao.tool.service.IUserService;
import com.taotao.tool.util.ApiAssertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author taotao
 * @since 2022-10-25
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public ApiResp<LoginResp> login(@RequestBody LoginReq loginReq, HttpServletResponse httpResp) {
        String username = loginReq.getUsername();
        String password = userService.encryptPassword(loginReq.getPassword());
        User user = userService.query().eq("username", username)
                .eq("password", password).one();
        ApiAssertUtils.notNull(user, "用户名或密码错误");
        LoginResp resp = new LoginResp();
        String token = userService.getToken(user.getId());
        resp.setId(user.getId());
        resp.setToken(token);
        // 设置 cookie
        addCookie(httpResp, "user_id", user.getId());
        addCookie(httpResp, "token", token);
        return ApiResp.success(resp);
    }

    private void addCookie(HttpServletResponse httpResp, String key, Object value) {
        Cookie cookie = new Cookie(key, value.toString());
        int maxAge = (int) TimeUnit.DAYS.toSeconds(1);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        httpResp.addCookie(cookie);
    }
}
