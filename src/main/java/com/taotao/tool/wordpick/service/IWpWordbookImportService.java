package com.taotao.tool.wordpick.service;

import com.taotao.tool.wordpick.dto.resp.WpWordbookImportResp;

public interface IWpWordbookImportService {

    WpWordbookImportResp importDataset(String dataset);
}
