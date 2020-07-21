package com.transaction.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class User {
    public User() {
        String time = DateTimeFormatter.ofPattern("HHmmss").format(LocalDateTime.now());
        this.name = "USER-" + time;
        this.crt_time = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S").format(LocalDateTime.now());
        this.address = "ADDRESS-" + time;
    }

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String crt_time;

    private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCrt_time() {
        return crt_time;
    }

    public void setCrt_time(String crt_time) {
        this.crt_time = crt_time;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name='" + name + '\'' + ", crt_time='" + crt_time + '\'' + ", address='"
            + address + '\'' + '}';
    }
}
