package com.taotao.tool.wordpick.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taotao.tool.wordpick.dto.req.WpSaveMarkReq;
import com.taotao.tool.wordpick.dto.resp.WpWordResp;
import com.taotao.tool.wordpick.model.WpUserWordMark;

import java.util.List;

public interface IWpWordMarkService extends IService<WpUserWordMark> {

    void saveMark(Integer userId, WpSaveMarkReq req);

    List<WpWordResp> listMark(Integer userId, Integer categoryId, Integer state);
}
