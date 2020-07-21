package com.lsl.user.entity;

import java.io.Serializable;

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
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    @TableField("username")
    private String username;

    /**
     * 年龄
     */
    @TableField("age")
    private Integer age;

    /**
     * 电话
     */
    @TableField("phone")
    private Long phone;

    /**
     * 描述
     */
    @TableField("mark")
    private String mark;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    @TableField("name")
    private String name;

    public static final String ID = "id";

    public static final String USERNAME = "username";

    public static final String AGE = "age";

    public static final String PHONE = "phone";

    public static final String MARK = "mark";

    public static final String EMAIL = "email";

    public static final String NAME = "name";

}
