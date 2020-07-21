package com.lsl.commons.lang.utils.db;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lsl.commons.lang.utils.io.ResourceFileUtil;

public class DBUtil {

    private static Logger logger = LoggerFactory.getLogger(DBUtil.class);

    public static String jdbcConfig = "db/db_config.properties" ;

    public static ThreadLocal<Connection> localConnection = new ThreadLocal<Connection>();

    private static Connection getConnection(Properties prop) {
        Connection conn = null;
        try {
            Class.forName(ResourceFileUtil.getStringValue(prop, "driverClassName"));
            String url = ResourceFileUtil.getStringValue(prop, "url");
            String username = ResourceFileUtil.getStringValue(prop, "db.username");
            String password = ResourceFileUtil.getStringValue(prop, "db.password");
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            logger.info("加载数据库驱动失败", e);
            e.printStackTrace();
        } catch (SQLException e) {
            logger.info("获取数据库连接失败", e);
            e.printStackTrace();
        }
        return conn;
    }

    public static Connection getConnection() {
        Connection conn = localConnection.get();
        try {
            if (conn == null || conn.isClosed()) {
                conn = getConnection(ResourceFileUtil.loadProperties(jdbcConfig));
                localConnection.set(conn);
            }
        } catch (Exception e) {
            logger.info("", e);
            e.printStackTrace();
        }
        return conn;
    }

    public static void release(Connection con, Statement statement, ResultSet rs) {
        if (null != rs) {
            try {
                rs.close();
            } catch (SQLException e) {
                logger.info("关闭ResultSet失败", e);
                e.printStackTrace();
            } finally {
                rs = null;
            }
        }
        if (null != statement) {
            try {
                statement.close();
            } catch (SQLException e) {
                logger.info("关闭Statement对象失败", e);
                e.printStackTrace();
            } finally {
                statement = null;
            }
        }
        if (null != con) {
            try {
                con.close();
            } catch (SQLException e) {
                logger.info("关闭数据库连接失败", e);
                e.printStackTrace();
            } finally {
                con = null;
            }
        }
    }

    public static void closeStatement(Statement statement) {
        release(null, statement, null);
    }

    public static void closeConnection(Connection con) {
        release(con, null, null);
    }

    public static void closeResultSet(ResultSet rs) {
        release(null, null, rs);
    }

    public static List<Map<String, Object>> queryForSql(String sqlName, Map<String, Object> param) {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Connection connection = DBUtil.getConnection();
        if (logger.isDebugEnabled()) {
            logger.debug("sql===>" + sqlName);
        }
        // Statement stm = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            connection.setAutoCommit(false);
            String sql = ""/*sqlBound.getSql()*/;
            if (logger.isDebugEnabled()) {
                logger.debug("sql:" + sql);
            }
            Map<Integer, Object> paramTable = null;
            stm = connection.prepareStatement(sql);
            Set<Integer> indexSet = paramTable.keySet();
            for (Integer index : indexSet) {
                Object V = paramTable.get(index);
                if (NumberUtils.isNumber(V.toString())) {
                    stm.setInt(index, Integer.parseInt(V.toString()));
                } else if (V instanceof String) {
                    stm.setString(index, (String)V);
                } else {
                    stm.setObject(index, paramTable.get(index));
                }
            }
            // stm.setFetchSize(1000);
            // rs = stm.executeQuery(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                Map<String, Object> rowResult = new HashMap<String, Object>();
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();
                for (int cousor = 1; cousor <= columnCount; cousor++) {
                    String columnLabel = metaData.getColumnLabel(cousor);
                    Object columnValue = rs.getObject(cousor);
                    rowResult.put(columnLabel, columnValue);
                }
                result.add(rowResult);
            }
        } catch (SQLException e) {
            logger.error("", e);
        } finally {
            DBUtil.release(connection, stm, rs);
        }
        connection = DBUtil.getConnection();
        DBUtil.release(connection, stm, rs);
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("PHONE", "136842526");
        param.put("EMAIL", "610039525@163.com");
        String sql = "SELECT * FROM TB_USER";
        List<Map<String, Object>> result = DBUtil.queryForSql("testNamespace.testSql2", param);
        System.out.println(result);
    }

}
