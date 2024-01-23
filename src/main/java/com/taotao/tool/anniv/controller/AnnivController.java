package com.taotao.tool.anniv.controller;

import com.taotao.tool.anniv.constant.EAnnivType;
import com.taotao.tool.anniv.dto.AnnivDTO;
import com.taotao.tool.anniv.model.Anniv;
import com.taotao.tool.anniv.service.IAnnivService;
import com.taotao.tool.common.dto.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author caojiantao
 * @since 2024-01-23
 */
@RestController
@RequestMapping("/anniv")
public class AnnivController {

    @Autowired
    private IAnnivService annivService;

    @GetMapping("/listAnniv")
    public ApiResult<List<AnnivDTO>> listAnniv() {
        List<AnnivDTO> list = annivService.list().stream()
                .map(
                        item -> AnnivDTO.builder()
                                .id(item.getId())
                                .annivType(EAnnivType.valueOf(item.getAnnivType()))
                                .annivDate(item.getAnnivDate().toString())
                                .lunar(item.getLunar())
                                .remark(item.getRemark())
                                .build()
                )
                .collect(Collectors.toList());
        return ApiResult.success(list);
    }

    @PostMapping("/saveAnniv")
    public ApiResult<Void> saveAnniv(@RequestBody AnnivDTO anniv) {
        Anniv record = new Anniv();
        record.setId(anniv.getId());
        record.setAnnivType(anniv.getAnnivType().name());
        record.setAnnivDate(LocalDate.parse(anniv.getAnnivDate()));
        record.setLunar(anniv.getLunar());
        record.setRemark(anniv.getRemark());
        annivService.saveOrUpdate(record);
        return ApiResult.success();
    }

    @PostMapping("/deleteAnniv")
    public ApiResult<Void> deleteAnniv(Integer id) {
        annivService.removeById(id);
        return ApiResult.success();
    }
}
