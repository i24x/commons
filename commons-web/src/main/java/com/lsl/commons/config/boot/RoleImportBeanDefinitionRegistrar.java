package com.lsl.commons.config.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 作用于类上 @Import(RoleImportBeanDefinitionRegistrar.class)
 */
public class RoleImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    private static Logger logger = LoggerFactory.getLogger(RoleImportBeanDefinitionRegistrar.class);

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        BeanDefinition beanDefinition = null;
        BeanDefinitionBuilder bdb = BeanDefinitionBuilder.rootBeanDefinition(Role.class);
        beanDefinition = bdb.getBeanDefinition();
        logger.info("Role->ImportBeanDefinitionRegistrar");
        registry.registerBeanDefinition("Role", beanDefinition);
    }

}
