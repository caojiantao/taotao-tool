package com.taotao.tool.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

public class FileUtils {

    /**
     * 解析文件类型
     */
    public static String parseExt(InputStream is) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(is);
        return URLConnection.guessContentTypeFromStream(bis);
    }
}
