package com.taotao.tool.carpool.service.impl;

import com.taotao.tool.carpool.model.CarpoolDriver;
import com.taotao.tool.carpool.mapper.CarpoolDriverMapper;
import com.taotao.tool.carpool.model.CarpoolMedia;
import com.taotao.tool.carpool.service.ICarpoolDriverService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taotao.tool.carpool.service.ICarpoolMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author caojiantao
 * @since 2023-12-21
 */
@Service
public class CarpoolDriverServiceImpl extends ServiceImpl<CarpoolDriverMapper, CarpoolDriver> implements ICarpoolDriverService {

    @Autowired
    private ICarpoolMediaService carpoolMediaService;

    @Override
    public CarpoolDriver getDriverByOpenid(String openid) {
        CarpoolDriver driver = query().eq("openid", openid).one();
        if (Objects.nonNull(driver)) {
            String license = carpoolMediaService.getMediaUrl(driver.getDriverLicense());
            String permit = carpoolMediaService.getMediaUrl(driver.getCarPermit());
            driver.setDriverLicense(license);
            driver.setCarPermit(permit);
        }
        return driver;
    }

    @Override
    public Map<String, CarpoolDriver> getDriverMapByOpenidList(List<String> openidList) {
        Map<String, CarpoolDriver> map = new HashMap<>();
        if (CollectionUtils.isEmpty(openidList)) {
            return map;
        }
        List<CarpoolDriver> list = query().in("openid", openidList).list();
        for (CarpoolDriver item : list) {
            String license = carpoolMediaService.getMediaUrl(item.getDriverLicense());
            String permit = carpoolMediaService.getMediaUrl(item.getCarPermit());
            item.setDriverLicense(license);
            item.setCarPermit(permit);
            map.put(item.getOpenid(), item);
        }
        return map;
    }
}
