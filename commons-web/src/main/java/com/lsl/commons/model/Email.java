package com.lsl.commons.model;

import java.io.Serializable;

public class Email implements Serializable {
    private static final long serialVersionUID = 1L;
    private String cuid;
    private String address;
    private String phone;
    private String destnation;
    private String content;
    private String title;
    static {
    }
    {
        this.address = "chengdu";
    }

    public String getCuid() {
        return cuid;
    }

    public void setCuid(String cuid) {
        this.cuid = cuid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDestnation() {
        return destnation;
    }

    public void setDestnation(String destnation) {
        this.destnation = destnation;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Email(String cuid, String address, String title) {
        this.cuid = cuid;
        this.address = address;
        this.title = title;
    }

    public Email() {
        this.cuid = "EMAIL-" + System.nanoTime();
        this.address = "SICHUAN CHENGDU";
        this.title = "ORDER-" + System.currentTimeMillis();
    }

    public static Email newDefaultInstance() {
        Email e = new Email("EMAIL-" + System.nanoTime(), "SICHUAN CHENGDU", "ORDER-" + System.currentTimeMillis());
        return e;
    }

    @Override
    public String toString() {
        return "Email [cuid=" + cuid + ", address=" + address + ", phone=" + phone + ", destnation=" + destnation
            + ", content=" + content + ", title=" + title + "]";
    }

}
