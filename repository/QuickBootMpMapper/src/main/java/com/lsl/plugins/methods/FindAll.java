package com.lsl.plugins.methods;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;

public class FindAll extends AbstractMethod {

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo table) {
        // 注入的SQL语句
        String selectAllSql = "select * from " + table.getTableName();
        String selectAllMethod = "selectAll";
        SqlSource selectAllSqlSource = languageDriver.createSqlSource(configuration, selectAllSql, modelClass);
        return this.addSelectMappedStatementForTable(mapperClass, selectAllMethod, selectAllSqlSource, table);
    }
}