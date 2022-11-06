package com.taotao.tool.util;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.util.FileCopyUtils;

import java.io.*;

@Slf4j
public class ThumbnailUtils {

    public static void compress(InputStream is, File target, double quality) {
        try (OutputStream os = new FileOutputStream(target)) {
            long length = is.available() / 1024;
            if (length < 1024) {
                FileCopyUtils.copy(is, os);
            } else {
                Thumbnails.of(is)
                        .scale(quality)
                        .outputQuality(quality)
                        .toFile(target);
            }
        } catch (Exception e) {
            log.error("压缩图片出现异常", e);
        }
    }
}
