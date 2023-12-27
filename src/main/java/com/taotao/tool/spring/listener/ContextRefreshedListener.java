package com.taotao.tool.spring.listener;

import com.taotao.tool.system.service.WorkWxService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private WorkWxService workWxService;

    @Override
    public void onApplicationEvent(@NotNull ContextRefreshedEvent event) {
        workWxService.sendMessage("application_start_message", null);
    }
}
