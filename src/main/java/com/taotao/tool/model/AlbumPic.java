package com.taotao.tool.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.taotao.tool.model.BaseModel;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author taotao
 * @since 2022-08-30
 */
@Getter
@Setter
@TableName("album_pic")
public class AlbumPic extends BaseModel {

    private Integer albumId;

    private Integer picId;
}
