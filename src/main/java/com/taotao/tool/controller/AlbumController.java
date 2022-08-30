package com.taotao.tool.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taotao.tool.dto.req.AddAlbumReq;
import com.taotao.tool.dto.req.AlbumPicPageReq;
import com.taotao.tool.dto.req.BasePageReq;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;
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
    public ApiResp<Integer> addAlbum(@RequestBody @Validated AddAlbumReq req) throws Exception {
        Album album = JsonUtils.convert(req, Album.class);
        boolean save = albumService.save(album);
        log.info("act=addAlbum req={} save={} id={}", JsonUtils.toJson(req), save, album.getId());
        ApiAssertUtils.notNull(album.getId(), "新建相册失败~");
        return ApiResp.success(album.getId());
    }

    @GetMapping("/getAlbumPage")
    public ApiResp<BasePageResp<Album>> getAlbumPage(BasePageReq req) {
        IPage<Album> page = new Page<>(req.getPage(), req.getSize());
        albumService.query()
                .orderByDesc("gmt_create")
                .page(page);
        BasePageResp<Album> resp = new BasePageResp<>(page.getRecords(), page.getTotal());
        return ApiResp.success(resp);
    }

    @GetMapping("/getAlbumPicPage")
    public ApiResp<BasePageResp<Pic>> getAlbumPicPage(AlbumPicPageReq req) {
        IPage<AlbumPic> page = new Page<>(req.getPage(), req.getSize());
        albumPicService.query()
                .eq("album_id", req.getAlbumId())
                .orderByDesc("gmt_create")
                .page(page);
        List<Integer> picIdList = page.getRecords().stream().map(AlbumPic::getPicId).collect(Collectors.toList());
        List<Pic> pics = picService.listByIds(picIdList);
        BasePageResp<Pic> resp = new BasePageResp<>(pics, page.getTotal());
        return ApiResp.success(resp);
    }

    @PostMapping("/batchUploadPic")
    public ApiResp<Void> batchUploadPic(Integer albumId, MultipartHttpServletRequest request) throws Exception {
        ApiAssertUtils.notNull(albumId, "未指定相册");
        Album album = albumService.getById(albumId);
        ApiAssertUtils.notNull(album, "上传相册不合法");
        List<Pic> picList = picService.doUpload(request);
        List<AlbumPic> albumPicList = picList.stream()
                .map(item -> {
                    AlbumPic albumPic = new AlbumPic();
                    albumPic.setAlbumId(albumId);
                    albumPic.setPicId(item.getId());
                    return albumPic;
                })
                .collect(Collectors.toList());
        boolean saveBatch = albumPicService.saveBatch(albumPicList);
        log.info("act=batchUploadPic picListSize={} saveBatch={}", albumPicList.size(), saveBatch);
        return ApiResp.success(null);
    }
}
