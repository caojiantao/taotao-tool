package com.taotao.tool.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taotao.tool.admin.mapper.DictionaryMapper;
import com.taotao.tool.admin.model.Dictionary;
import com.taotao.tool.admin.service.IDictionaryService;
import com.taotao.tool.common.util.JsonUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author taotao
 * @since 2023-09-19
 */
@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary> implements IDictionaryService {

    @Override
    public void saveOrUpdateByKey(Dictionary dictionary) {
        boolean exists = query()
                .eq("item_key", dictionary.getItemKey())
                .exists();
        if (exists) {
            update()
                    .eq("item_key", dictionary.getItemKey())
                    .update(dictionary);
        } else {
            save(dictionary);
        }
    }

    @Override
    public <T> T getValueByKey(String key, Class<T> clazz) {
        Dictionary dictionary = query()
                .eq("item_key", key)
                .one();
        if (Objects.isNull(dictionary)) {
            return null;
        }
        return JsonUtils.convert(dictionary.getItemValue(), clazz);
    }
}
