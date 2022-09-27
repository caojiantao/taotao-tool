package com.taotao.tool.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taotao.tool.model.Pic;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author taotao
 * @since 2022-08-22
 */
public interface IPicService extends IService<Pic> {

    Pic getByMd5(String md5);
}
