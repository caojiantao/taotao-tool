package com.taotao.tool.event.upload;

import com.taotao.tool.model.File;
import com.taotao.tool.service.IFileService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
@Component
public class Listener implements ApplicationListener<Event> {

    @Autowired
    private IFileService fileService;

    @Async
    @Override
    public void onApplicationEvent(@NotNull Event event) {
        Integer fileId = (Integer) event.getSource();
        log.info("act=onUploadEvent fileId={}", fileId);
        try {
            String ext = fileService.parseFileExt(fileId);
            if (!StringUtils.hasLength(ext)) {
                return;
            }
            File updateFile = new File();
            updateFile.setId(fileId);
            updateFile.setExt(ext);
            updateFile.setGmtModified(LocalDateTime.now());
            boolean update = fileService.updateById(updateFile);
            log.info("act=onUploadEvent fileId={} ext={} update={}", fileId, ext, update);
        } catch (IOException e) {
            log.error("act=onUploadEvent fileId={}", fileId, e);
        }
    }
}
