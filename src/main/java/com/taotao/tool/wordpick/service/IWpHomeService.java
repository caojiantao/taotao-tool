package com.taotao.tool.wordpick.service;

import com.taotao.tool.wordpick.dto.resp.WpHomeResp;

public interface IWpHomeService {

    WpHomeResp getHome(Integer userId);
}
