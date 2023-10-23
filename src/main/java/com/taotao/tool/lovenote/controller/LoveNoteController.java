package com.taotao.tool.lovenote.controller;

import com.taotao.tool.admin.dto.resp.LoveNoteLoginResp;
import com.taotao.tool.common.dto.ApiResult;
import com.taotao.tool.common.exception.ApiException;
import com.taotao.tool.common.util.ApiAssertUtils;
import com.taotao.tool.lovenote.constant.ELoveNoteHomeFeedType;
import com.taotao.tool.lovenote.entity.*;
import com.taotao.tool.lovenote.model.LoveNoteCp;
import com.taotao.tool.lovenote.model.LoveNoteUser;
import com.taotao.tool.lovenote.other.LoveNoteLoginApi;
import com.taotao.tool.lovenote.other.LoveNoteLoginUtils;
import com.taotao.tool.lovenote.service.ILoveNoteCpService;
import com.taotao.tool.lovenote.service.ILoveNoteTrendService;
import com.taotao.tool.lovenote.service.ILoveNoteUserService;
import com.taotao.tool.spring.yml.UploadYml;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Validated
@RestController
@RequestMapping("/love-note")
public class LoveNoteController {

    @Autowired
    private UploadYml uploadYml;

    @Autowired
    private ILoveNoteTrendService loveNoteTrendService;

    @Autowired
    private ILoveNoteUserService loveNoteUserService;

    @Autowired
    private ILoveNoteCpService loveNoteCpService;

    @PostMapping("/login")
    public ApiResult<LoveNoteLoginResp> login(@NotEmpty String code) {
        LoveNoteLoginResp resp = loveNoteUserService.login(code);
        return ApiResult.success(resp);
    }

    @PostMapping("/register")
    public ApiResult<LoveNoteLoginResp> register(@RequestBody @Validated LoveNoteUserRegisterRequest request) {
        LoveNoteLoginResp resp = loveNoteUserService.register(request);
        return ApiResult.success(resp);
    }

    @LoveNoteLoginApi
    @PostMapping("/saveUser")
    public ApiResult<Void> saveUser(@RequestBody LoveNoteUser user) {
        String openid = LoveNoteLoginUtils.getCurrentUser().getOpenid();
        loveNoteUserService.update().eq("openid", openid).update(user);
        return ApiResult.success();
    }

    @PostMapping("/uploadImage")
    public ApiResult<String> uploadImage(@RequestPart MultipartFile file) {
        String fname = file.getOriginalFilename();
        String suffix = StringUtils.hasText(fname) ? fname.substring(fname.lastIndexOf(".")) : ".jpg";
        UploadYml.Image image = uploadYml.getImage();
        String filename = UUID.randomUUID() + suffix;
        File outFile = new File(image.getPath() + "/love-note", filename);
        try (InputStream is = file.getInputStream();
             OutputStream os = Files.newOutputStream(outFile.toPath())) {
            StreamUtils.copy(is, os);
        } catch (Exception e) {
            log.error("上传图片异常", e);
            throw new ApiException(-1, e.getMessage());
        }
        return ApiResult.success(filename);
    }

    @LoveNoteLoginApi
    @PostMapping("/addTrend")
    public ApiResult<Void> addTrend(@RequestBody LoveNoteTrendDto trendDto) {
        loveNoteTrendService.addTrend(trendDto);
        return ApiResult.success();
    }

    @GetMapping("/getHomeFeedList")
    public ApiResult<List<LoveNoteHomeFeed>> getHomeFeedList(LoveNoteTrendQuery query) {
        List<LoveNoteTrendVo> trendList = loveNoteTrendService.getLoveNoteTrendList(query);
        List<LoveNoteHomeFeed> feedList = trendList.stream().map(item -> new LoveNoteHomeFeed(ELoveNoteHomeFeedType.TREND, item)).collect(Collectors.toList());
        return ApiResult.success(feedList);
    }

    @LoveNoteLoginApi
    @PostMapping("/agreeCp")
    public ApiResult<Void> agreeCp(String inviterOpenid) {
        LoveNoteUser inviter = loveNoteUserService.getUserByOpenid(inviterOpenid);
        LoveNoteUser invitee = LoveNoteLoginUtils.getCurrentUser();
        ApiAssertUtils.notNull(inviter, "inviter 不合法");
        loveNoteCpService.addCp(inviter, invitee);
        return ApiResult.success();
    }

    @GetMapping("/getUserByOpenid")
    public ApiResult<LoveNoteUserDto> getUserByOpenid(String openid) {
        LoveNoteUserDto userDto = new LoveNoteUserDto();
        LoveNoteUser user = loveNoteUserService.getUserByOpenid(openid);
        userDto.setUser(user);
        LoveNoteCp userCp = loveNoteCpService.getCpByOpenid(openid);
        userDto.setUserCp(userCp);
        if (Objects.nonNull(userCp)) {
            String partnerOpenid = Objects.equals(openid, userCp.getInviter())
                    ? userCp.getInvitee() : userCp.getInviter();
            LoveNoteUser userPartner = loveNoteUserService.getUserByOpenid(partnerOpenid);
            userDto.setUserPartner(userPartner);
        }
        return ApiResult.success(userDto);
    }
}
