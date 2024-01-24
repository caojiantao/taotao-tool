package com.taotao.tool.anniv.job;

import com.taotao.tool.anniv.model.Anniv;
import com.taotao.tool.anniv.service.IAnnivService;
import com.taotao.tool.common.util.JsonUtils;
import com.taotao.tool.system.service.WorkWxService;
import com.tyme.lunar.LunarDay;
import com.tyme.solar.SolarDay;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Properties;

@Slf4j
@Component
public class AnnivJob {

    @Autowired
    private IAnnivService annivService;
    @Autowired
    private WorkWxService workWxService;

    @Scheduled(cron = "0 0 8 * * ?")
    public void annivNotice() {
        for (Anniv anniv : annivService.list()) {
            if (!matchDay(anniv)) {
                continue;
            }
            log.info("今天是TA的纪念日 {}", JsonUtils.toJson(anniv));
            Properties properties = new Properties();
            properties.put("type", anniv.getAnnivType());
            properties.put("date", String.valueOf(anniv.getAnnivDate()));
            properties.put("lunar", String.valueOf(anniv.getLunar()));
            properties.put("remark", anniv.getRemark());
            workWxService.sendMessage("anniv_notice_message", properties);
        }
    }

    private boolean matchDay(Anniv anniv) {
        LocalDate now = LocalDate.now();
        int nowYear = now.getYear();
        int nowMonth = now.getMonthValue();
        int nowDay = now.getDayOfMonth();
        LocalDate annivDate = anniv.getAnnivDate();
        int annivMonth = annivDate.getMonthValue();
        int annivDay = annivDate.getDayOfMonth();
        if (anniv.getLunar()) {
            SolarDay solarDay = SolarDay.fromYmd(nowYear, nowMonth, nowDay);
            LunarDay lunarDay = solarDay.getLunarDay();
            nowMonth = lunarDay.getMonth().getMonth();
            nowDay = lunarDay.getDay();
        }
        return Objects.equals(nowMonth, annivMonth) && Objects.equals(nowDay, annivDay);
    }
}
