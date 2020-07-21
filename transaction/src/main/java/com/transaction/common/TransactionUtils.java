package com.transaction.common;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.aop.framework.AopContext;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;

@Component
public class TransactionUtils {
    @Autowired
    DataSource dataSource;

    @Autowired
    PlatformTransactionManager transactionManager;

    @Autowired
    TransactionDefinition definition;

    public Connection getConnection() {
        Connection connection;
        try {
            connection = DataSourceUtils.doGetConnection(dataSource);
            System.out.println("connection ======>" + connection);
        } catch (SQLException e) {
            throw new RuntimeException("get Connection Exception " + e.getMessage());
        }
        return connection;
    }

    public <T> T getProxyObject() {
        return (T)AopContext.currentProxy();
    }

    public boolean isAopProxy(Object object) {
        return AopUtils.isAopProxy(object);
    }
}
