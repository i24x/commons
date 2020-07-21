package com.lsl.dsp.chain;

import java.util.ArrayList;
import java.util.List;

public class MdInvocationTest {

    public static void main(String[] args) {
        List<MdInterceptor> interceptors = new ArrayList<>();
        MdInvocation context = new MdInvocation(interceptors);
        MdBeforeInterceptor beforeInterceptor = new MdBeforeInterceptor();
        MdAfterInterceptor afterInterceptor = new MdAfterInterceptor();
        MdBeforeInterceptor beforeInterceptor2 = new MdBeforeInterceptor();
        interceptors.add(beforeInterceptor);
        interceptors.add(beforeInterceptor2);
        interceptors.add(afterInterceptor);

        context.process();
    }
}
