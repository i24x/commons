package com.lsl.commons.config.boot.aop.advisor;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdvisorConfig {

    @Bean(name = "OrderAdvisor")
    public DefaultPointcutAdvisor initOrderAdvisor(@Qualifier("aspectJPointCut") Pointcut pointcut,
        @Qualifier("OrderAdvice") Advice advice) {
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setAdvice(advice);
        advisor.setPointcut(pointcut);
        return advisor;
    }

    /* @Bean
    public AspectJExpressionPointcutAdvisor pointcutAdvisor(@Qualifier("OrderAdvice") Advice advice){
      AspectJExpressionPointcutAdvisor pointcutAdvisor = new AspectJExpressionPointcutAdvisor();
      pointcutAdvisor.setAdvice(advice);
      pointcutAdvisor.setExpression("execution (* com.lsl.commons.service..*Impl.*(..))");
      Pointcut pointcut = pointcutAdvisor.getPointcut();      
      return pointcutAdvisor;
    }*/
}
