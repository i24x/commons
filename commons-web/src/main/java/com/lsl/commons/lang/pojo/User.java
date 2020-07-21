package com.lsl.commons.lang.pojo;

import java.util.function.Function;

public class User {

    public int id;
    public String name;
    public String sex;
    public String age;
    public String phone;
    public String address;

    public User() {
        super();
        this.name = "HanMeiMei";
        this.sex = "F";
        this.age = "18";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User [name=" + name + "]";
    }

    public static void print(String s) {
        System.out.println(s);
    }

    // Function<T,R>
    public String get(String s) {
        return s;
    }

    public void say(String name, Function<String, String> action) {
        System.out.println("name-" + System.identityHashCode(name) + ":" + action.apply(name));
    }

    public void sayGoodBye() {
        System.out.println("sayGoodBye");
    }

    public User(String name, String phone) {
        super();
        this.name = name;
        this.phone = phone;
    }

}
