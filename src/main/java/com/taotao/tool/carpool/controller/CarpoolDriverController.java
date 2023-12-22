package com.taotao.tool.carpool.controller;

import com.taotao.tool.carpool.entity.CarpoolDriverSaveReq;
import com.taotao.tool.carpool.model.CarpoolDriver;
import com.taotao.tool.carpool.other.CarpoolLoginApi;
import com.taotao.tool.carpool.service.ICarpoolDriverService;
import com.taotao.tool.carpool.service.ICarpoolMediaService;
import com.taotao.tool.common.dto.ApiResult;
import com.taotao.tool.common.util.JsonUtils;
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
@RequestMapping("/carpool/driver")
public class CarpoolDriverController {

    @Autowired
    private ICarpoolDriverService carpoolDriverService;

    @Autowired
    private ICarpoolMediaService carpoolMediaService;

    @GetMapping("/getDriverByOpenid")
    public ApiResult<CarpoolDriver> getDriverByOpenid(@NotEmpty String openid) {
        CarpoolDriver driver = carpoolDriverService.getDriverByOpenid(openid);
        return ApiResult.success(driver);
    }

    @CarpoolLoginApi
    @PostMapping("/saveDriver")
    public ApiResult<Void> saveDriver(@RequestBody @Validated CarpoolDriverSaveReq req) {
        String license = carpoolMediaService.getMediaFileName(req.getDriverLicense());
        String permit = carpoolMediaService.getMediaFileName(req.getCarPermit());
        CarpoolDriver driver = carpoolDriverService.getDriverByOpenid(req.getOpenid());
        if (Objects.isNull(driver)) {
            driver = JsonUtils.convert(req, CarpoolDriver.class);
            driver.setDriverLicense(license);
            driver.setCarPermit(permit);
            carpoolDriverService.save(driver);
        } else {
            driver.setDriverLicense(license);
            driver.setCarPermit(permit);
            carpoolDriverService.update().eq("openid", req.getOpenid())
                    .update(driver);
        }
        return ApiResult.success();
    }
}
