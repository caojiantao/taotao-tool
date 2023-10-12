package com.taotao.tool.admin.service;

import com.taotao.tool.common.util.PlaceHolderUtils;
import com.taotao.tool.common.util.QyApiUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Properties;

@Slf4j
@Service
public class WorkWxService {

    @Autowired
    private IDictionaryService dictionaryService;

    /**
     * 发送企业微信群消息
     *
     * @param key        推送模板的 key
     * @param properties 推送内容，用来替换模板占位符
     */
    public void sendMessage(String key, Properties properties) {
        String template = dictionaryService.getValueByKey(key, String.class);
        String webhook = dictionaryService.getValueByKey("work_wx_webhook", String.class);
        if (!StringUtils.hasText(template) || !StringUtils.hasText(webhook)) {
            log.info("推送模板不存在或者webhook未配置，不发送消息");
            return;
        }
        String markdown = PlaceHolderUtils.replacePlaceholders(template, properties);
        QyApiUtils.sendMessage(webhook, markdown);
    }
}
