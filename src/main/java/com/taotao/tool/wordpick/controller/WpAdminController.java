package com.taotao.tool.wordpick.controller;

import com.taotao.tool.common.dto.ApiResult;
import com.taotao.tool.common.util.TTAssertUtils;
import com.taotao.tool.wordpick.dto.req.WpWordbookImportReq;
import com.taotao.tool.wordpick.dto.resp.WpWordbookImportResp;
import com.taotao.tool.wordpick.service.IWpWordbookImportService;
import com.taotao.tool.wordpick.yml.WpImportYml;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/wp/dev/wordbook")
public class WpAdminController {

    private static final String IMPORT_TOKEN_HEADER = "X-Wordpick-Import-Token";
    private static final String DATASET_PATTERN = "[a-z0-9-]+";

    @Autowired
    private WpImportYml importYml;

    @Autowired
    private IWpWordbookImportService importService;

    @PostMapping("/import")
    public ApiResult<WpWordbookImportResp> importWordbook(
            @RequestHeader(value = IMPORT_TOKEN_HEADER, required = false) String token,
            @RequestBody WpWordbookImportReq req) {
        TTAssertUtils.isTrue(StringUtils.hasText(importYml.getToken()), "词库导入 token 未配置");
        TTAssertUtils.isTrue(importYml.getToken().equals(token), "词库导入 token 错误");
        TTAssertUtils.notNull(req, "导入参数不能为空");
        TTAssertUtils.isTrue(StringUtils.hasText(req.getDataset()), "dataset 不能为空");
        TTAssertUtils.isTrue(req.getDataset().matches(DATASET_PATTERN), "dataset 格式错误");
        log.info("wordbookImport_param, dataset={}", req.getDataset());
        return ApiResult.success(importService.importDataset(req.getDataset()));
    }
}
