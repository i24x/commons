package com.lsl.article.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Chrow Yeung
 * @since 2020-02-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("article")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("name")
    private String name;

    @TableField("content")
    private String content;

    @TableField("author")
    private String author;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("crt_user")
    private String crtUser;

    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String CONTENT = "content";

    public static final String AUTHOR = "author";

    public static final String CREATE_TIME = "create_time";

    public static final String CRT_USER = "crt_user";

}
