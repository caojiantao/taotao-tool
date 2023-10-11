package com.taotao.tool.lovenote.service;

import com.taotao.tool.lovenote.entity.LoveNoteTrendQuery;
import com.taotao.tool.lovenote.entity.LoveNoteTrendDto;
import com.taotao.tool.lovenote.entity.LoveNoteTrendVo;
import com.taotao.tool.lovenote.model.LoveNoteTrend;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author taotao
 * @since 2023-10-11
 */
public interface ILoveNoteTrendService extends IService<LoveNoteTrend> {

    void addTrend(LoveNoteTrendDto trendDto);

    void removeTrendById(Integer id);

    List<LoveNoteTrendVo> getLoveNoteTrendList(LoveNoteTrendQuery query);
}
