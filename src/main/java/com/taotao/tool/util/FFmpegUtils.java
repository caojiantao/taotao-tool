package com.taotao.tool.util;

import org.bytedeco.javacv.FFmpegFrameGrabber;

import java.io.File;
import java.util.function.Consumer;

public class FFmpegUtils {

    public static void parseVideo(File file, Consumer<FFmpegFrameGrabber> consumer) throws FFmpegFrameGrabber.Exception {
        FFmpegFrameGrabber grabber = null;
        try {
            grabber = new FFmpegFrameGrabber(file);
            grabber.start();
            consumer.accept(grabber);
        } finally {
            if (grabber != null) {
                grabber.stop();
                grabber.release();
            }
        }
    }
}
