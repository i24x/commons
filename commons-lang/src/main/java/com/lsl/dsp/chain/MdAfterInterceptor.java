package com.lsl.dsp.chain;

public class MdAfterInterceptor implements MdInterceptor {

    @Override
    public void invoke(MdInvocation context) {
        context.process();
        System.out.println("MdAfterInterceptor:" + context.index);
    }
}
