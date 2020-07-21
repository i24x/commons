package com.lsl.commons.lang.utils.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//@Component
public class MybatisUtil {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    private static SqlSessionTemplate sqlSessionTemplate;

    @Resource(name = "sqlSessionTemplate")
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        MybatisUtil.sqlSessionTemplate = sqlSessionTemplate;
    }

    public static void update(String statement, Map<String, Object> parameter) {
        sqlSessionTemplate.update(statement, parameter);
    }

    public static List<Map<String, Object>> queryBySql(String statement) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("statement", statement);
        List<Map<String, Object>> list = sqlSessionTemplate.selectList("GenericDaoMapper.queryBySql", param);
        return list;
    }

    public static int updateBySql(String statement) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("statement", statement);
        int num = sqlSessionTemplate.update("GenericDaoMapper.updateBySql", param);
        return num;
    }
}
