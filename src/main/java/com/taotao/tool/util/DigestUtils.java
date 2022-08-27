package com.taotao.tool.util;

import java.nio.charset.StandardCharsets;

public class DigestUtils extends org.springframework.util.DigestUtils {

    public static String md5(String content) {
        byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
        return org.springframework.util.DigestUtils.md5DigestAsHex(bytes);
    }
}
