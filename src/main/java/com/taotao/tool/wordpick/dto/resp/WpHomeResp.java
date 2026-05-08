package com.taotao.tool.wordpick.dto.resp;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class WpHomeResp {

    private WpHomeSummaryResp summary;
    private List<WpCategoryResp> categories;
}
