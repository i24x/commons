package com.lsl.commons.config.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 返回注入类全路径注入容器 作用于注解上
 * 
 * @author lsl
 */
public class RoleImportSelector implements ImportSelector {
    private static Logger logger = LoggerFactory.getLogger(RoleImportSelector.class);

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        logger.info("ImportRole'name:" + importingClassMetadata.getAnnotationAttributes(ImportRole.class.getName()));
        return new String[] {"com.lsl.commons.config.boot.Role", Role.class.getName()};
    }

}
