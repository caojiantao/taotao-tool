package com.taotao.tool.wordpick.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taotao.tool.wordpick.dto.resp.WpCategoryResp;
import com.taotao.tool.wordpick.dto.resp.WpChapterResp;
import com.taotao.tool.wordpick.dto.resp.WpWordResp;
import com.taotao.tool.wordpick.model.WpCategory;

import java.util.List;

public interface IWpCategoryService extends IService<WpCategory> {

    List<WpCategoryResp> listCategories();

    List<WpChapterResp> listChapters(Integer categoryId);

    List<WpWordResp> listWords(Integer chapterId);
}
