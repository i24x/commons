package com.lsl.dsp.chain;

public class MdBeforeInterceptor implements MdInterceptor {

    @Override
    public void invoke(MdInvocation context) {
        try {
            System.out.println("MdBeforeInterceptor:" + context.index);
        } finally {
            context.process();
        }
    }
}
