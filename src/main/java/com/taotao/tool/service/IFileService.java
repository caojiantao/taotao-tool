package com.taotao.tool.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taotao.tool.model.File;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author taotao
 * @since 2022-09-30
 */
public interface IFileService extends IService<File> {

    File getByMd5(String md5);

    List<File> doBatchUpload(List<MultipartFile> files) throws Exception;

    String parseFileExt(Integer fileId) throws IOException;
}
