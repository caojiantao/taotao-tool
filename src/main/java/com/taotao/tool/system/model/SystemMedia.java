package com.taotao.tool.system.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author caojiantao
 * @since 2023-12-27
 */
@Getter
@Setter
@TableName("system_media")
public class SystemMedia {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String bucket;

    private String mediaType;

    private String filename;

    private String contentMd5;

    private Long contentLength;

    private String contentJson;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
