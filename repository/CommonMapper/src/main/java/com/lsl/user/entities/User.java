package com.lsl.user.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class User {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 电话
     */
    private Long phone;

    /**
     * 描述
     */
    private String mark;

    /**
     * 邮箱
     */
    private String email;

    private String name;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id
     *            主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户名
     *
     * @return username - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username
     *            用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取年龄
     *
     * @return age - 年龄
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置年龄
     *
     * @param age
     *            年龄
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 获取电话
     *
     * @return phone - 电话
     */
    public Long getPhone() {
        return phone;
    }

    /**
     * 设置电话
     *
     * @param phone
     *            电话
     */
    public void setPhone(Long phone) {
        this.phone = phone;
    }

    /**
     * 获取描述
     *
     * @return mark - 描述
     */
    public String getMark() {
        return mark;
    }

    /**
     * 设置描述
     *
     * @param mark
     *            描述
     */
    public void setMark(String mark) {
        this.mark = mark;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email
     *            邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username='" + username + '\'' + ", age=" + age + ", phone=" + phone
            + ", mark='" + mark + '\'' + ", email='" + email + '\'' + ", name='" + name + '\'' + '}';
    }
}