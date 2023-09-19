package com.taotao.tool.service;

import com.taotao.tool.model.Dictionary;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author taotao
 * @since 2023-09-19
 */
public interface IDictionaryService extends IService<Dictionary> {

    void saveOrUpdateByKey(Dictionary dictionary);

    <T> T getValueByKey(String key, Class<T> clazz);
}