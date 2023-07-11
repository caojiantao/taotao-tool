package com.taotao.tool.listener;

import com.taotao.tool.util.SnowFlakeUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class ContextRefreshListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        long epoch = LocalDateTime.of(2022, 10, 12, 0, 0, 0)
                .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        int datacenterBit = 0;
        int workerBit = 0;
        int sequenceBit = 2;
        SnowFlakeUtils.init(epoch, datacenterBit, workerBit, sequenceBit);
    }
}
