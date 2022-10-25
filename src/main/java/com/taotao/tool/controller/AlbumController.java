package com.taotao.tool.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.Lists;
import com.taotao.tool.dto.AlbumFileDTO;
import com.taotao.tool.dto.req.AddAlbumReq;
import com.taotao.tool.dto.req.AlbumFilePageReq;
import com.taotao.tool.dto.req.BasePageReq;
import com.taotao.tool.dto.resp.AlbumDetailResp;
import com.taotao.tool.dto.resp.AlbumHomeResp;
import com.taotao.tool.dto.resp.ApiResp;
import com.taotao.tool.dto.resp.BasePageResp;
import com.taotao.tool.enums.EFileType;
import com.taotao.tool.model.Album;
import com.taotao.tool.model.AlbumFile;
import com.taotao.tool.model.File;
import com.taotao.tool.service.IAlbumFileService;
import com.taotao.tool.service.IAlbumService;
import com.taotao.tool.service.IFileService;
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
    private IFileService fileService;

    @PostMapping("/addAlbum")
    public ApiResp<Integer> addAlbum(@Validated AddAlbumReq req, @RequestPart("file") MultipartFile multipartFile) throws Exception {
        Album album = JsonUtils.convert(req, Album.class);
        File file = fileService.doBatchUpload(Lists.newArrayList(multipartFile)).get(0);
        album.setCoverId(file.getId());
        LocalDateTime now = LocalDateTime.now();
        album.setGmtCreate(now);
        album.setGmtModified(now);
        boolean save = albumService.save(album);
        log.info("act=addAlbum req={} save={} id={}", JsonUtils.toJson(req), save, album.getId());
        ApiAssertUtils.notNull(album.getId(), "新建相册失败~");
        return ApiResp.success(album.getId());
    }

    @GetMapping("/getAlbumHomeResp")
    public ApiResp<AlbumHomeResp> getAlbumHomeResp() {
        Long albumNum = albumService.count();
        Long imageNum = albumFileService.query().eq("file_type", EFileType.image).count();
        Long videoNum = albumFileService.query().eq("file_type", EFileType.video).count();
        AlbumHomeResp resp = new AlbumHomeResp(albumNum, imageNum, videoNum);
        return ApiResp.success(resp);
    }

    @GetMapping("/getAlbumById")
    public ApiResp<AlbumDetailResp> getAlbumById(Integer albumId) throws Exception {
        Album album = albumService.getById(albumId);
        if (Objects.isNull(album)) {
            return ApiResp.success(null);
        }
        AlbumDetailResp resp = JsonUtils.convert(album, AlbumDetailResp.class);
        List<AlbumFile> fileList = albumFileService.query().eq("album_id", albumId).list();
        long picNum = fileList.stream().filter(item -> Objects.equals(EFileType.image, item.getFileType())).count();
        long videoNum = fileList.stream().filter(item -> Objects.equals(EFileType.video, item.getFileType())).count();
        resp.setPicNum(picNum);
        resp.setVideoNum(videoNum);
        return ApiResp.success(resp);
    }

    @GetMapping("/getAlbumPage")
    public ApiResp<BasePageResp<AlbumDetailResp>> getAlbumPage(@Validated BasePageReq req) throws JsonProcessingException {
        IPage<Album> page = new Page<>(req.getCurrent(), req.getSize());
        albumService.query().orderByDesc("gmt_create").page(page);
        List<Album> records = page.getRecords();
        if (CollectionUtils.isEmpty(records)) {
            BasePageResp<AlbumDetailResp> resp = new BasePageResp<>(Lists.newArrayList(), page.getTotal());
            return ApiResp.success(resp);
        }
        List<AlbumDetailResp> rows = JsonUtils.convert(records, AlbumDetailResp.class);
        Map<Integer, AlbumDetailResp> rowsMap = rows.stream().collect(Collectors.toMap(AlbumDetailResp::getId, Function.identity()));
        // 封面
        List<Integer> coverIdList = records.stream().map(Album::getCoverId).collect(Collectors.toList());
        List<File> coverFileList = fileService.listByIds(coverIdList);
        Map<Integer, String> picMap = coverFileList.stream().collect(Collectors.toMap(File::getId, File::getFilename));
        rows.forEach(item -> item.setCoverFilename(picMap.get(item.getCoverId())));
        // 图片视频数量
        List<Integer> albumIdList = records.stream().map(Album::getId).collect(Collectors.toList());
        List<AlbumFile> albumFileList = albumFileService.query().in("album_id", albumIdList).list();
        albumFileList.forEach(file -> {
            AlbumDetailResp album = rowsMap.get(file.getAlbumId());
            if (EFileType.image.equals(file.getFileType())) {
                album.setPicNum(album.getPicNum() + 1);
            } else if (EFileType.video.equals(file.getFileType())) {
                album.setVideoNum(album.getVideoNum() + 1);
            }
        });
        BasePageResp<AlbumDetailResp> resp = new BasePageResp<>(rows, page.getTotal());
        return ApiResp.success(resp);
    }

    @GetMapping("/getAlbumFilePage")
    public ApiResp<BasePageResp<AlbumFileDTO>> getAlbumFilePage(@Validated AlbumFilePageReq req) {
        IPage<AlbumFile> page = new Page<>(req.getCurrent(), req.getSize());
        QueryChainWrapper<AlbumFile> wrapper = albumFileService.query().eq("album_id", req.getAlbumId()).orderByDesc("gmt_create");
        if (Objects.nonNull(req.getFileType())) {
            wrapper.eq("file_type", req.getFileType());
        }
        wrapper.page(page);
        List<Integer> fileIdList = page.getRecords().stream().map(AlbumFile::getFileId).collect(Collectors.toList());
        List<AlbumFileDTO> albumFiles = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(fileIdList)) {
            Map<Integer, File> fileMap = fileService.listByIds(fileIdList).stream().collect(Collectors.toMap(File::getId, Function.identity()));
            albumFiles = page.getRecords().stream().map(item -> {
                File file = fileMap.get(item.getFileId());
                AlbumFileDTO dto = JsonUtils.convert(file, AlbumFileDTO.class);
                dto.setAlbumFileId(item.getId());
                return dto;
            }).collect(Collectors.toList());
        }
        BasePageResp<AlbumFileDTO> resp = new BasePageResp<>(albumFiles, page.getTotal());
        return ApiResp.success(resp);
    }

    @PostMapping("/batchUploadFile")
    public ApiResp<Void> batchUploadFile(
            @RequestParam Integer albumId,
            @RequestPart List<MultipartFile> files
    ) throws Exception {
        ApiAssertUtils.notNull(albumId, "未指定相册");
        Album album = albumService.getById(albumId);
        ApiAssertUtils.notNull(album, "上传相册不存在");

        List<File> fileList = fileService.doBatchUpload(files);
        List<AlbumFile> albumFileList = fileList.stream().map(item -> {
            AlbumFile albumFile = new AlbumFile();
            albumFile.setAlbumId(albumId);
            albumFile.setFileId(item.getId());
            albumFile.setFileType(item.getFileType());
            albumFile.setGmtCreate(LocalDateTime.now());
            albumFile.setGmtModified(LocalDateTime.now());
            return albumFile;
        }).collect(Collectors.toList());
        boolean saveBatch = albumFileService.saveBatch(albumFileList);
        log.info("act=batchUploadFile fileListSize={} saveBatch={}", albumFileList.size(), saveBatch);
        return ApiResp.success(null);
    }

    @PostMapping("/batchRemoveFile")
    public ApiResp<Void> batchRemoveFile(
            @RequestParam Integer albumId,
            @RequestBody List<Integer> albumFileIdList
    ) {
        albumFileService.removeBatchByIds(albumFileIdList);
        return ApiResp.success(null);
    }
}
