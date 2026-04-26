package com.taotao.tool.wordpick.service;

import com.taotao.tool.wordpick.dto.resp.WpWordResp;

import java.util.List;

public interface IWpWordService {

    List<WpWordResp> listWordRespByIds(List<Integer> wordIds);
}
