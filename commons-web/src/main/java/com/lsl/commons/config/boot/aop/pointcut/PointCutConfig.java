package com.lsl.commons.config.boot.aop.pointcut;

import org.springframework.aop.Pointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PointCutConfig {
    // @Bean("JdkPointCut")
    // public JdkRegexpMethodPointcut initJdkPointCut() {
    // JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
    // pointcut.setPatterns("\\.\\*update\\*");
    // return pointcut;
    // }
    private static final String AOP_POINTCUT_EXPRESSION = "execution(* com.lsl..*Impl.*update*(..))";

    @Bean(name = "aspectJPointCut")
    public Pointcut aspectJPointCut() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        return pointcut;
    }
}
