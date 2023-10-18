package com.taotao.tool.lovenote.entity;

import com.taotao.tool.lovenote.constant.ELoveNoteHomeFeedType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoveNoteHomeFeed {

    private ELoveNoteHomeFeedType type;

    private LoveNoteTrendVo trend;
}
