package com.lsl.commons.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

//@Repository("GenericDAO")
public class GenericDAOImpl implements GenericDAO {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public Map<String, Object> selectFirstOne(String statement) {
        List<Map<String, Object>> list = this.querySql(statement);
        Map<String, Object> result = null;
        if (list != null && list.size() > 0) {
            result = list.get(0);
        }
        return result;
    }

    @Override
    public List<Map<String, Object>> querySql(String statement) {
        Map<String, Object> param = new HashMap<String, Object>(5, 0.75f);
        param.put("statement", statement);
        param.size();
        List<Map<String, Object>> list = sqlSessionTemplate.selectList("GenericDaoMapper.queryBySql", param);
        return list;
    }

    @Override
    public int updateSql(String statement) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("statement", statement);
        int num = sqlSessionTemplate.update("GenericDaoMapper.updateBySql", param);
        return num;
    }

    @Override
    public Map<String, Object> selectJustOne(String statement) {
        List<Map<String, Object>> list = this.querySql(statement);
        if (list != null && list.size() > 1) {
            throw new RuntimeException("返回结果太多");
        } else {
            return CollectionUtils.isEmpty(list) ? null : list.get(0);
        }
    }
}
