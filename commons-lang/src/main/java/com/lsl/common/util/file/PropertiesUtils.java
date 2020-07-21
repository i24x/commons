package com.lsl.common.util.file;

import java.util.Properties;

public abstract class PropertiesUtils {

    private static final String ZKCONFIG_PATH = "zookeeper.properties";

    public static Properties getProperties(String path) {
        Properties pro = new Properties();
        try {
            pro.load(PropertiesUtils.class.getClassLoader().getResourceAsStream(path));
        } catch (Exception e) {
            System.err.println(path + "文件不存在,原因：" + e.getMessage());
            throw new RuntimeException(path + "文件不存在,原因：" + e.getMessage(), e);
        }
        return pro;
    }

    public static int getInt(Properties prop, String key) {
        String value = prop.getProperty(key);
        if (value == null || value.trim().equals("")) {
            throw new RuntimeException("属性" + key + "未配置");
        }
        return Integer.valueOf(value);
    }

    public static String getString(Properties prop, String key) {
        String value = prop.getProperty(key);
        if (value == null || value.trim().equals("")) {
            throw new RuntimeException("属性" + key + "未配置");
        }
        return value;
    }

    public static String getZkUrl() {
        return getProperties(ZKCONFIG_PATH).getProperty("zk.url");
    }

    public static String getZkTimeOut() {
        return getProperties(ZKCONFIG_PATH).getProperty("time.out");
    }

    public static String getString(String path, String key) {
        return getProperties(path).getProperty(key);
    }

    public static <T> T get(String path, String key) {
        return (T)getProperties(path).getProperty(key);
    }

    public static void main(String[] args) {
        System.out.println(getZkUrl());
        System.out.println(getZkTimeOut());
        System.out.println(PropertiesUtils.<Integer>get(ZKCONFIG_PATH, "time.out"));
    }

    public abstract static class ConfigPath {

        public static final String LOCALHOST = "localhost";

        public static final String REDIS_CONFIG_PATH = "redis.properties";
        public static final String REDIS_HOST = "host";
        public static final String REDIS_PORT = "port";

        public static final String MYSQL_CONFIG_PATH = "mysql.properties";

    }
}
