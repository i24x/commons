package com.lsl.commons.config.boot.aop.proxy;

import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutoProxyCreatorConfig {
    /*@Bean("proxyOrderService")
    public BeanNameAutoProxyCreator initProxyBean(@Qualifier("OrderService") OrderService orderService) {
    //			ProxyFactoryBean proxyBean = new ProxyFactoryBean();
    //			proxyBean.setTarget(orderService);
    //			proxyBean.setInterceptorNames("OrderAdvisor");
    //			proxyBean.setInterfaces(IOrderService.class);
    		
    		BeanNameAutoProxyCreator nameProxy = new BeanNameAutoProxyCreator();
    		nameProxy.setBeanNames("\\*");
    	return nameProxy;
    }*/
    @Bean(name = "DefaultAdvisorAutoProxyCreator")
    public DefaultAdvisorAutoProxyCreator initAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator autoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        autoProxyCreator.setProxyTargetClass(true);
        return autoProxyCreator;
    }
}
