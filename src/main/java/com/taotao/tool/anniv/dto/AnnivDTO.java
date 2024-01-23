package com.taotao.tool.anniv.dto;

import com.taotao.tool.anniv.constant.EAnnivType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnnivDTO {
    private Integer id;

    private EAnnivType annivType;

    private String annivDate;

    private Boolean lunar;

    private String remark;
}
