package com.taotao.tool.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.taotao.tool.dto.req.BasePageReq;
import com.taotao.tool.dto.resp.ApiResp;
import com.taotao.tool.dto.resp.BasePageResp;
import com.taotao.tool.model.Pic;
import com.taotao.tool.service.IPicService;
import com.taotao.tool.util.ApiAssertUtils;
import com.taotao.tool.util.DigestUtils;
import com.taotao.tool.util.FileUtils;
import com.taotao.tool.yml.PicYml;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author taotao
 * @since 2022-08-22
 */
@Slf4j
@RestController
@RequestMapping("/pic")
public class PicController {

    @Autowired
    private PicYml picYml;
    @Autowired
    private IPicService picService;

    @PostMapping("/batchUploadPic")
    public ApiResp<List<Pic>> batchUploadPic(MultipartHttpServletRequest request) throws Exception {
        String dir = picYml.getPath();
        Files.createDirectories(Paths.get(dir));
        MultiValueMap<String, MultipartFile> multiFileMap = request.getMultiFileMap();
        List<MultipartFile> files = multiFileMap.values().stream().flatMap(Collection::stream).collect(Collectors.toList());
        ApiAssertUtils.notEmpty(files, "上传图片不能为空");
        List<Pic> picList = Lists.newArrayList();
        for (MultipartFile file : files) {
            String filename = file.getOriginalFilename();
            String ext, md5;
            try (InputStream is = file.getInputStream()) {
                ext = FileUtils.parseExt(is);
                md5 = DigestUtils.md5DigestAsHex(is);
            }
            Pic originPic = getPicByMd5(md5);
            if (Objects.nonNull(originPic)) {
                log.info("act=batchUploadPic type=alreadyExist filename={}", filename);
                continue;
            }
            String newFilename = DigestUtils.md5(UUID.randomUUID().toString()) + "." + ext;
            File newFile = new File(dir, newFilename);
            file.transferTo(newFile);
            log.info("act=batchUploadPic type=saveDisk filename={} newFilename={}", filename, newFilename);
            Pic pic = new Pic();
            pic.setUrl(newFilename);
            pic.setMd5(md5);
            pic.setGmtCreate(LocalDateTime.now());
            pic.setGmtModified(LocalDateTime.now());
            picService.save(pic);
            log.info("act=batchUploadPic type=saveDb filename={} newFilename={} id={}", filename, newFilename, pic.getId());
            picList.add(pic);
        }
        return ApiResp.success(picList);
    }

    @GetMapping("/getPicPage")
    public ApiResp<BasePageResp<Pic>> getPicPage(@Validated BasePageReq req) {
        IPage<Pic> page = new Page<>(req.getPage(), req.getSize());
        picService.query()
                .orderByDesc("gmt_create")
                .page(page);
        BasePageResp<Pic> resp = new BasePageResp<>(page.getRecords(), page.getTotal());
        return ApiResp.success(resp);
    }

    private Pic getPicByMd5(String md5) {
        return picService.query()
                .eq("md5", md5)
                .one();
    }
}
