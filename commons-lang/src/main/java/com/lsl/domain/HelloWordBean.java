package com.lsl.domain;

/**
 * <p>
 * 描述:
 * </p>
 *
 * @author yangcao
 * @version v1.0
 * @date 2020/7/31 20:30
 */
public class HelloWordBean {

    private String var;

    public String sayHello() {
        return "Hello Word";
    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }
}
