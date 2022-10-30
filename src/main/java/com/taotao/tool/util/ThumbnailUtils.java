package com.taotao.tool.util;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.util.FileCopyUtils;

import java.io.File;

@Slf4j
public class ThumbnailUtils {

    public static void compress(File source, File target, double quality) {
        try {
            long length = source.length() / 1024;
            if (length < 1024) {
                FileCopyUtils.copy(source, target);
            } else {
                Thumbnails.of(source)
                        .scale(quality)
                        .outputQuality(quality)
                        .toFile(target);
            }
        } catch (Exception e) {
            log.error("压缩图片出现异常", e);
        }
    }
}
