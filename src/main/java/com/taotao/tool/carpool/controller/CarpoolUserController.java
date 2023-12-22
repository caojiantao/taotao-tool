package com.taotao.tool.carpool.controller;

import com.taotao.tool.carpool.entity.CarpoolLoginResp;
import com.taotao.tool.carpool.entity.CarpoolUserDto;
import com.taotao.tool.carpool.entity.CarpoolUserRegisterReq;
import com.taotao.tool.carpool.model.CarpoolDriver;
import com.taotao.tool.carpool.model.CarpoolLine;
import com.taotao.tool.carpool.model.CarpoolUser;
import com.taotao.tool.carpool.service.ICarpoolDriverService;
import com.taotao.tool.carpool.service.ICarpoolLineService;
import com.taotao.tool.carpool.service.ICarpoolMediaService;
import com.taotao.tool.carpool.service.ICarpoolUserService;
import com.taotao.tool.common.dto.ApiResult;
import com.taotao.tool.common.util.JsonUtils;
import com.taotao.tool.lovenote.other.LoveNoteLoginApi;
import com.taotao.tool.lovenote.other.LoveNoteLoginUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author caojiantao
 * @since 2023-12-21
 */
@Controller
@RequestMapping("/carpool/user")
public class CarpoolUserController {

    @Autowired
    private ICarpoolUserService carpoolUserService;
    @Autowired
    private ICarpoolMediaService carpoolMediaService;
    @Autowired
    private ICarpoolDriverService carpoolDriverService;
    @Autowired
    private ICarpoolLineService carpoolLineService;

    @PostMapping("/login")
    public ApiResult<CarpoolLoginResp> login(@NotEmpty String code) {
        CarpoolLoginResp resp = carpoolUserService.login(code);
        return ApiResult.success(resp);
    }

    @PostMapping("/register")
    public ApiResult<CarpoolLoginResp> register(@RequestBody @Validated CarpoolUserRegisterReq request) {
        CarpoolLoginResp resp = carpoolUserService.register(request);
        return ApiResult.success(resp);
    }

    @LoveNoteLoginApi
    @PostMapping("/saveUser")
    public ApiResult<Void> saveUser(@RequestBody CarpoolUser user) {
        String openid = LoveNoteLoginUtils.getCurrentUser().getOpenid();
        String mediaFileName = carpoolMediaService.getMediaFileName(user.getAvatar());
        user.setAvatar(mediaFileName);
        carpoolUserService.update().eq("openid", openid).update(user);
        return ApiResult.success();
    }

    @GetMapping("/getUserByOpenid")
    public ApiResult<CarpoolUserDto> getUserByOpenid(String openid) {
        CarpoolUser user = carpoolUserService.getUserByOpenid(openid);
        if (Objects.isNull(user)) {
            return ApiResult.success();
        }
        CarpoolUserDto userDto = JsonUtils.convert(user, CarpoolUserDto.class);
        CarpoolDriver driver = carpoolDriverService.getDriverByOpenid(openid);
        userDto.setDriver(driver);
        CarpoolLine line = carpoolLineService.getLineByOpenid(openid);
        userDto.setLine(line);
        return ApiResult.success(userDto);
    }
}
