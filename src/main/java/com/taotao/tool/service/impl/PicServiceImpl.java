package com.taotao.tool.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.taotao.tool.mapper.PicMapper;
import com.taotao.tool.model.Pic;
import com.taotao.tool.service.IPicService;
import com.taotao.tool.util.ApiAssertUtils;
import com.taotao.tool.util.DigestUtils;
import com.taotao.tool.util.FileUtils;
import com.taotao.tool.yml.PicYml;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author taotao
 * @since 2022-08-22
 */
@Slf4j
@Service
public class PicServiceImpl extends ServiceImpl<PicMapper, Pic> implements IPicService {

    @Autowired
    private PicYml picYml;

    @Override
    public List<Pic> doUpload(List<MultipartFile> files) throws Exception {
        ApiAssertUtils.notEmpty(files, "上传图片不能为空");
        ApiAssertUtils.isTrue(files.size() <= 20, "上传图片数量不能超过 20");
        String picDiskPath = getPicDiskPath();
        List<Pic> picList = Lists.newArrayList();
        for (MultipartFile file : files) {
            String filename = file.getOriginalFilename();
            String contentType, md5;
            try (InputStream is = file.getInputStream()) {
                contentType = FileUtils.parseExt(is);
                md5 = DigestUtils.md5DigestAsHex(is);
            }
            Pic originPic = getByMd5(md5);
            if (Objects.nonNull(originPic)) {
                log.info("act=batchUploadPic type=alreadyExist filename={}", filename);
                picList.add(originPic);
                continue;
            }
            String ext = contentType.substring(contentType.lastIndexOf("/") + 1);
            String newFilename = DigestUtils.md5(UUID.randomUUID().toString()) + "." + ext;
            File newFile = new File(picDiskPath, newFilename);
            file.transferTo(newFile);
            log.info("act=batchUploadPic type=saveDisk filename={} newFilename={}", filename, newFilename);
            Pic pic = new Pic();
            pic.setFilename(newFilename);
            pic.setMd5(md5);
            pic.setBytes(newFile.length());
            pic.setContentType(contentType);
            pic.setGmtCreate(LocalDateTime.now());
            pic.setGmtModified(LocalDateTime.now());
            save(pic);
            log.info("act=batchUploadPic type=saveDb filename={} newFilename={} id={}", filename, newFilename, pic.getId());
            picList.add(pic);
        }
        return picList;
    }

    @Override
    public Pic getByMd5(String md5) {
        return query()
                .eq("md5", md5)
                .one();
    }

    private String getPicDiskPath() throws IOException {
        String dir = picYml.getPath();
        Files.createDirectories(Paths.get(dir));
        return dir;
    }
}
