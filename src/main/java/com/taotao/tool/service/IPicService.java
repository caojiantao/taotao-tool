package com.taotao.tool.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taotao.tool.model.Pic;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author taotao
 * @since 2022-08-22
 */
public interface IPicService extends IService<Pic> {

    List<Pic> doUpload(MultipartHttpServletRequest request) throws Exception;

    Pic getByMd5(String md5);
}
