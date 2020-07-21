package com.lsl.commons.config.boot;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 作用于注解上获取注解信息
 * 
 * @Import(CacheImportBeanDefinitionRegistrar.class) 注册BeanProcessor通过获取注解属性注入packages->BeanProcessor实现类
 */
public class CacheImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    private static Logger logger = LoggerFactory.getLogger(CacheImportBeanDefinitionRegistrar.class);

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        BeanDefinitionBuilder bdb = BeanDefinitionBuilder.rootBeanDefinition(CacheBeanPostProcessor.class);
        Map<String, Object> enableCacheAttributes =
            importingClassMetadata.getAnnotationAttributes(EnableCache.class.getName());
        String[] packArr = (String[])enableCacheAttributes.get("packages");
        List<String> packages = Arrays.asList(packArr);
        bdb.addPropertyValue("packages", packages);
        BeanDefinition beanDefinition = bdb.getBeanDefinition();
        logger.info("注册BeanProcessor通过获取注解属性注入packages->BeanProcessor实现类");
        registry.registerBeanDefinition(CacheBeanPostProcessor.class.getName(), beanDefinition);
    }

}
