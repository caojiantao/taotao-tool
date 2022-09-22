package com.taotao.tool.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taotao.tool.model.Pic;
import org.springframework.web.multipart.MultipartFile;

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

    List<Pic> doUpload(List<MultipartFile> files) throws Exception;

    Pic getByMd5(String md5);
}
