package com.taotao.tool.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.taotao.tool.dto.req.AddAlbumReq;
import com.taotao.tool.dto.req.AlbumFilePageReq;
import com.taotao.tool.dto.req.BasePageReq;
import com.taotao.tool.dto.resp.AlbumResp;
import com.taotao.tool.dto.resp.ApiResp;
import com.taotao.tool.dto.resp.BasePageResp;
import com.taotao.tool.dto.resp.FileResp;
import com.taotao.tool.enums.EFileType;
import com.taotao.tool.model.Album;
import com.taotao.tool.model.AlbumFile;
import com.taotao.tool.model.Pic;
import com.taotao.tool.model.Video;
import com.taotao.tool.service.IAlbumFileService;
import com.taotao.tool.service.IAlbumService;
import com.taotao.tool.service.IPicService;
import com.taotao.tool.service.IVideoService;
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
import java.util.Objects;
import java.util.function.Function;
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
    private IAlbumFileService albumFileService;
    @Autowired
    private IPicService picService;
    @Autowired
    private IVideoService videoService;

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

    @GetMapping("/getAlbumById")
    public ApiResp<AlbumResp> getAlbumById(Integer albumId) throws Exception {
        Album album = albumService.getById(albumId);
        if (Objects.isNull(album)) {
            return ApiResp.success(null);
        }
        AlbumResp resp = JsonUtils.convert(album, AlbumResp.class);
        List<AlbumFile> fileList = albumFileService.query().eq("album_id", albumId).list();
        long picNum = fileList.stream().filter(item -> Objects.equals(EFileType.PICTURE, item.getFileType())).count();
        long videoNum = fileList.stream().filter(item -> Objects.equals(EFileType.VIDEO, item.getFileType())).count();
        resp.setPicNum(picNum);
        resp.setVideoNum(videoNum);
        return ApiResp.success(resp);
    }

    @GetMapping("/getAlbumPage")
    public ApiResp<BasePageResp<AlbumResp>> getAlbumPage(@Validated BasePageReq req) throws JsonProcessingException {
        IPage<Album> page = new Page<>(req.getCurrent(), req.getSize());
        albumService.query().orderByDesc("gmt_create").page(page);
        List<Album> records = page.getRecords();
        if (CollectionUtils.isEmpty(records)) {
            BasePageResp<AlbumResp> resp = new BasePageResp<>(Lists.newArrayList(), page.getTotal());
            return ApiResp.success(resp);
        }
        List<AlbumResp> rows = JsonUtils.convert(records, AlbumResp.class);
        Map<Integer, AlbumResp> rowsMap = rows.stream().collect(Collectors.toMap(AlbumResp::getId, Function.identity()));
        // 封面
        List<Integer> coverIdList = records.stream().map(Album::getCoverId).collect(Collectors.toList());
        List<Pic> picList = picService.listByIds(coverIdList);
        Map<Integer, String> picMap = picList.stream().collect(Collectors.toMap(Pic::getId, Pic::getFilename));
        rows.forEach(item -> item.setCoverFilename(picMap.get(item.getCoverId())));
        // 图片视频数量
        List<Integer> albumIdList = records.stream().map(Album::getId).collect(Collectors.toList());
        List<AlbumFile> fileList = albumFileService.query().in("album_id", albumIdList).list();
        fileList.forEach(file -> {
            AlbumResp album = rowsMap.get(file.getAlbumId());
            if (EFileType.PICTURE.equals(file.getFileType())) {
                album.setPicNum(album.getPicNum() + 1);
            } else if (EFileType.VIDEO.equals(file.getFileType())) {
                album.setVideoNum(album.getVideoNum() + 1);
            }
        });
        BasePageResp<AlbumResp> resp = new BasePageResp<>(rows, page.getTotal());
        return ApiResp.success(resp);
    }

    @GetMapping("/getAlbumFilePage")
    public ApiResp<BasePageResp<FileResp>> getAlbumFilePage(@Validated AlbumFilePageReq req) {
        IPage<AlbumFile> page = new Page<>(req.getCurrent(), req.getSize());
        QueryChainWrapper<AlbumFile> wrapper = albumFileService.query().eq("album_id", req.getAlbumId()).orderByDesc("gmt_create");
        if (Objects.nonNull(req.getFileType())) {
            wrapper.eq("file_type", req.getFileType());
        }
        wrapper.page(page);
        Map<Integer, Pic> picMap = Maps.newHashMap();
        Map<Integer, Video> videoMap = Maps.newHashMap();
        List<Integer> picIdList = Lists.newArrayList();
        List<Integer> videoIdList = Lists.newArrayList();
        for (AlbumFile file : page.getRecords()) {
            if (EFileType.PICTURE.equals(file.getFileType())) {
                picIdList.add(file.getFileId());
            } else if (EFileType.VIDEO.equals(file.getFileType())) {
                videoIdList.add(file.getFileId());
            }
        }
        if (!CollectionUtils.isEmpty(picIdList)) {
            List<Pic> picList = picService.listByIds(picIdList);
            picMap = picList.stream().collect(Collectors.toMap(Pic::getId, Function.identity()));
        }
        if (!CollectionUtils.isEmpty(videoIdList)) {
            List<Video> videoList = videoService.listByIds(videoIdList);
            videoMap = videoList.stream().collect(Collectors.toMap(Video::getId, Function.identity()));
        }
        List<FileResp> fileList = Lists.newArrayList();
        for (AlbumFile file : page.getRecords()) {
            FileResp fileResp = new FileResp();
            if (EFileType.PICTURE.equals(file.getFileType())) {
                fileResp.setFileType(EFileType.PICTURE);
                fileResp.setPic(picMap.get(file.getFileId()));
            } else if (EFileType.VIDEO.equals(file.getFileType())) {
                fileResp.setFileType(EFileType.VIDEO);
                fileResp.setVideo(videoMap.get(file.getFileId()));
            }
            if (Objects.nonNull(fileResp.getFileType())) {
                fileList.add(fileResp);
            }
        }
        BasePageResp<FileResp> resp = new BasePageResp<>(fileList, page.getTotal());
        return ApiResp.success(resp);
    }

    @PostMapping("/batchUploadPic")
    public ApiResp<Void> batchUploadPic(Integer albumId, EFileType fileType, @RequestPart List<MultipartFile> files) throws Exception {
        ApiAssertUtils.notNull(albumId, "未指定相册");
        Album album = albumService.getById(albumId);
        ApiAssertUtils.notNull(album, "上传相册不合法");
        List<Pic> picList = picService.doUpload(files);
        List<AlbumFile> albumFileList = picList.stream().map(item -> {
            AlbumFile albumFile = new AlbumFile();
            albumFile.setAlbumId(albumId);
            albumFile.setFileId(item.getId());
            albumFile.setFileType(EFileType.PICTURE);
            albumFile.setGmtCreate(LocalDateTime.now());
            albumFile.setGmtModified(LocalDateTime.now());
            return albumFile;
        }).collect(Collectors.toList());
        boolean saveBatch = albumFileService.saveBatch(albumFileList);
        log.info("act=batchUploadPic picListSize={} saveBatch={}", albumFileList.size(), saveBatch);
        return ApiResp.success(null);
    }
}
