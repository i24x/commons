package com.transaction.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.persistence.*;

import cn.hutool.system.SystemUtil;
import lombok.Data;

@Entity
@Table(name = "T_EQUIPMENT")
@Data
public class Equipment {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "type")
    private String type;

    @Column(name = "code")
    private String code;

    @Column(name = "category")
    private String category;

    @Column(name = "mac")
    private String mac;

    @Column(name = "phone")
    private String phone;

    @Column(name = "phone")
    private Date createTime;

    @Column(name = "createUser")
    private String createUser;

    public Equipment() {
        String time = DateTimeFormatter.ofPattern("HHmmss").format(LocalDateTime.now());
        this.name = "Equipment-" + time;
        this.code = "code-" + DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss.SSS").format(LocalDateTime.now());
        this.createUser = SystemUtil.getUserInfo().getName();
    }

    public static void main(String[] args) {
        Equipment eq = new Equipment();
        System.out.println(eq);
    }
}
