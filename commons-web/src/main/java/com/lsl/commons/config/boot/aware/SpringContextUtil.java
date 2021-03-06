package com.lsl.commons.config.boot.aware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextUtil implements ApplicationContextAware {

    public static ApplicationContext ctx;

    public static <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        T bean = SpringContextUtil.ctx.getBean(name, requiredType);
        return bean;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.ctx = applicationContext;
    }

    public ApplicationContext getCtx() {
        return SpringContextUtil.ctx;
    }
}
