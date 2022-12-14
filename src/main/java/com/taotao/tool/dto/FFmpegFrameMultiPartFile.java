package com.taotao.tool.dto;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.UUID;

public class FFmpegFrameMultiPartFile implements MultipartFile {

    private BufferedImage bufferedImage;
    private byte[] bytes;

    public FFmpegFrameMultiPartFile(FFmpegFrameGrabber grabber) throws Exception {
        double rotate = grabber.getDisplayRotation();
        Frame frame = grabber.grabImage();
        Java2DFrameConverter java2DFrameConverter = new Java2DFrameConverter();
        this.bufferedImage = java2DFrameConverter.getBufferedImage(frame);
        if (rotate != 0D) {
            int w = bufferedImage.getWidth(), h = bufferedImage.getHeight();
            double r = rotate % 360;
            int dw = w, dh = h;
            if (Math.abs(r / 90) % 2 == 1) {
                dw = h;
                dh = w;
            }
            BufferedImage rotateImage = new BufferedImage(h, w, bufferedImage.getType());
            Graphics2D g2 = rotateImage.createGraphics();
            g2.translate((dw - w) >> 1, (dh - h) >> 1);
            g2.rotate(Math.toRadians(-r), w >> 1, h >> 1);
            g2.drawImage(bufferedImage, 0, 0, null);
            g2.dispose();
            this.bufferedImage = rotateImage;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpeg", baos);
        this.bytes = baos.toByteArray();
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getOriginalFilename() {
        return UUID.randomUUID() + ".jpeg";
    }

    @Override
    public String getContentType() {
        return "image/jpeg";
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public long getSize() {
        return bytes.length;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return bytes;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        return bais;
    }

    @Override
    public void transferTo(@NotNull File dest) throws IOException, IllegalStateException {
        ImageIO.write(bufferedImage, "jpeg", dest);
    }
}
