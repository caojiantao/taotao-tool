package com.taotao.tool.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.Lists;
import com.taotao.tool.dto.req.AddAlbumReq;
import com.taotao.tool.dto.req.AlbumPicPageReq;
import com.taotao.tool.dto.req.BasePageReq;
import com.taotao.tool.dto.resp.AlbumResp;
import com.taotao.tool.dto.resp.ApiResp;
import com.taotao.tool.dto.resp.BasePageResp;
import com.taotao.tool.model.Album;
import com.taotao.tool.model.AlbumPic;
import com.taotao.tool.model.Pic;
import com.taotao.tool.service.IAlbumPicService;
import com.taotao.tool.service.IAlbumService;
import com.taotao.tool.service.IPicService;
import com.taotao.tool.util.ApiAssertUtils;
import com.taotao.tool.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author taotao
 * @since 2022-08-30
 */
@Slf4j
@RestController
@RequestMapping("/album")
public class AlbumController {

    @Autowired
    private IAlbumService albumService;
    @Autowired
    private IAlbumPicService albumPicService;
    @Autowired
    private IPicService picService;

    @PostMapping("/addAlbum")
    public ApiResp<Integer> addAlbum(@Validated AddAlbumReq req, @RequestPart MultipartFile file) throws Exception {
        Album album = JsonUtils.convert(req, Album.class);
        List<Pic> picList = picService.doUpload(Lists.newArrayList(file));
        album.setCoverId(picList.get(0).getId());
        LocalDateTime now = LocalDateTime.now();
        album.setGmtCreate(now);
        album.setGmtModified(now);
        boolean save = albumService.save(album);
        log.info("act=addAlbum req={} save={} id={}", JsonUtils.toJson(req), save, album.getId());
        ApiAssertUtils.notNull(album.getId(), "新建相册失败~");
        return ApiResp.success(album.getId());
    }

    @GetMapping("/getAlbumPage")
    public ApiResp<BasePageResp<AlbumResp>> getAlbumPage(@Validated BasePageReq req) throws JsonProcessingException {
        IPage<Album> page = new Page<>(req.getPage(), req.getSize());
        albumService.query()
                .orderByDesc("gmt_create")
                .page(page);
        List<Album> records = page.getRecords();
        List<Integer> coverIdList = records.stream().map(Album::getCoverId).collect(Collectors.toList());
        List<Pic> picList = picService.listByIds(coverIdList);
        Map<Integer, String> picMap = picList.stream().collect(Collectors.toMap(Pic::getId, Pic::getFilename));
        List<AlbumResp> rows = JsonUtils.convert(records, AlbumResp.class);
        rows.forEach(item -> item.setCoverFilename(picMap.get(item.getCoverId())));
        BasePageResp<AlbumResp> resp = new BasePageResp<>(rows, page.getTotal());
        return ApiResp.success(resp);
    }

    @GetMapping("/getAlbumPicPage")
    public ApiResp<BasePageResp<Pic>> getAlbumPicPage(@Validated AlbumPicPageReq req) {
        IPage<AlbumPic> page = new Page<>(req.getPage(), req.getSize());
        albumPicService.query()
                .eq("album_id", req.getAlbumId())
                .orderByDesc("gmt_create")
                .page(page);
        List<Integer> picIdList = page.getRecords().stream().map(AlbumPic::getPicId).collect(Collectors.toList());
        List<Pic> pics = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(picIdList)) {
            pics = picService.listByIds(picIdList);
        }
        BasePageResp<Pic> resp = new BasePageResp<>(pics, page.getTotal());
        return ApiResp.success(resp);
    }

    @PostMapping("/batchUploadPic")
    public ApiResp<Void> batchUploadPic(Integer albumId, @RequestPart List<MultipartFile> files) throws Exception {
        ApiAssertUtils.notNull(albumId, "未指定相册");
        Album album = albumService.getById(albumId);
        ApiAssertUtils.notNull(album, "上传相册不合法");
        List<Pic> picList = picService.doUpload(files);
        List<AlbumPic> albumPicList = picList.stream()
                .map(item -> {
                    AlbumPic albumPic = new AlbumPic();
                    albumPic.setAlbumId(albumId);
                    albumPic.setPicId(item.getId());
                    albumPic.setGmtCreate(LocalDateTime.now());
                    albumPic.setGmtModified(LocalDateTime.now());
                    return albumPic;
                })
                .collect(Collectors.toList());
        boolean saveBatch = albumPicService.saveBatch(albumPicList);
        log.info("act=batchUploadPic picListSize={} saveBatch={}", albumPicList.size(), saveBatch);
        return ApiResp.success(null);
    }
}
