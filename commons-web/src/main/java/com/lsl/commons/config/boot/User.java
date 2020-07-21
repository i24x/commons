package com.lsl.commons.config.boot;

import org.springframework.stereotype.Component;

@Component
public class User {
    public String addr;

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "User [addr=" + addr + ", getAddr()=" + getAddr() + ", getClass()=" + getClass() + ", hashCode()="
            + hashCode() + ", toString()=" + super.toString() + "]";
    }

}
