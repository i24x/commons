package com.lsl.dsp.chain;

import java.util.List;

public class MdInvocation {
    List<MdInterceptor> interceptors;
    public int index = -1;

    public void process() {
        if (index < 0) {
            index = interceptors.size();
        }

        if (index == 0) {
            System.out.println("执行主业务操作");
        }

        if (index > 0) {
            interceptors.get(--index).invoke(this);
        }

    }

    public MdInvocation(List<MdInterceptor> interceptors) {
        this.interceptors = interceptors;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public MdInvocation() {

    }

    public List<MdInterceptor> getInterceptors() {
        return interceptors;
    }

    public void setInterceptors(List<MdInterceptor> interceptors) {
        this.interceptors = interceptors;
    }
}
