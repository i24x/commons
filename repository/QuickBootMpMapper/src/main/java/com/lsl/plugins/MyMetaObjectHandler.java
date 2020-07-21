package com.lsl.plugins;

// import com.baomidou.mybatisplus.mapper.MetaObjectHandler;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

/**
 * 自定义公共字段填充处理器
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 插入操作 自动填充
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        Object fieldValue = getFieldValByName("crtUser", metaObject);
        System.out
            .println("************自动新增填充公共字段信息" + fieldValue + fieldValue + "->" + System.getProperty("user.name"));
        if (fieldValue == null) {
            setFieldValByName("crtUser", System.getProperty("user.name"), metaObject);
        }
    }

    /**
     * 修改操作 自动填充
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        Object fieldValue = getFieldValByName("crtUser", metaObject);
        System.out.println("************自动更新填充公共字段信息" + fieldValue + "->" + System.getProperty("user.name"));
        if (fieldValue == null) {
            setFieldValByName("crtUser", System.getProperty("user.name"), metaObject);
        }
    }
}
