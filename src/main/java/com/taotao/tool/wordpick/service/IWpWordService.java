package com.taotao.tool.wordpick.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taotao.tool.wordpick.dto.resp.WpWordResp;
import com.taotao.tool.wordpick.model.WpWord;

import java.util.List;

public interface IWpWordService extends IService<WpWord> {

    List<WpWordResp> listWordRespByIds(List<Integer> wordIds);
}
