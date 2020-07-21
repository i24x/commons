package com.lsl.account.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author Chrow Yeung
 * @since 2020-02-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_account")
public class TAccount extends Model<TAccount> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 姓名
     */
    @TableField("name")
    private String name;

    /**
     * 机构
     */
    @TableField("org")
    private String org;

    /**
     * 备注
     */
    @TableField("mark")
    private String mark;

    /**
     * 创建人
     */
    @TableField(value = "crt_user", fill = FieldFill.INSERT_UPDATE)
    private String crtUser;

    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String ORG = "org";

    public static final String MARK = "mark";

    public static final String CRT_USER = "crt_user";

}
