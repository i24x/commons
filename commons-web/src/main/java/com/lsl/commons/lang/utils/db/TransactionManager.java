package com.lsl.commons.lang.utils.db;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransactionManager {

    private static Logger logger = LoggerFactory.getLogger(TransactionManager.class);

    public static void begin() {
        Connection conn = DBUtil.getConnection();
        try {
            if (conn != null && !conn.isClosed() && conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
        } catch (SQLException e) {
            logger.info("", e);
            e.printStackTrace();
        }
    }

    public static void commit() {
        Connection conn = DBUtil.getConnection();
        try {
            if (conn != null && !conn.isClosed() && !conn.getAutoCommit()) {
                conn.commit();
            }
        } catch (SQLException e) {
            logger.info("", e);
            e.printStackTrace();
        }
    }

    public static void rollBack() {
        Connection conn = DBUtil.getConnection();
        try {
            if (conn != null && !conn.isClosed()) {
                conn.rollback();
            }
        } catch (SQLException e) {
            logger.info("", e);
            e.printStackTrace();
        }
    }

}
