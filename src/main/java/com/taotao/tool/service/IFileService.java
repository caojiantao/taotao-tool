package com.taotao.tool.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taotao.tool.dto.req.UploadFileReq;
import com.taotao.tool.model.File;

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

    List<File> doUploadFile(List<UploadFileReq.FileItem> fileItemList) throws IOException;
}
