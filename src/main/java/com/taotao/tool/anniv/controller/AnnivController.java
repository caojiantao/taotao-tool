package com.taotao.tool.anniv.controller;

import com.taotao.tool.anniv.model.Anniv;
import com.taotao.tool.anniv.service.IAnnivService;
import com.taotao.tool.common.dto.ApiResult;
import com.taotao.tool.system.annotation.RequireLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ApiResult<List<Anniv>> listAnniv() {
        List<Anniv> list = annivService.list();
        return ApiResult.success(list);
    }

    @PostMapping("/saveAnniv")
    public ApiResult<Void> saveAnniv(@RequestBody Anniv anniv) {
        annivService.saveOrUpdate(anniv);
        return ApiResult.success();
    }

    @PostMapping("/deleteAnniv")
    public ApiResult<Void> deleteAnniv(Integer id) {
        annivService.removeById(id);
        return ApiResult.success();
    }
}
