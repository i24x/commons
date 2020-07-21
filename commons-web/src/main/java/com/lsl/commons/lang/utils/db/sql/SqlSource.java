package com.lsl.commons.lang.utils.db.sql;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.CollectionUtils;

/**
 * 解析{@link StatementParser} 封装{@link StatementDomInfo} 用法{@link BaseQueryDao#queryForList(String, Map)}
 */
public class SqlSource {
    // private static String defauleLocation ="/sqlMap/User_sqlMap.xml";
    private static String defauleLocation = "/com/boco/**/dao/sqlmap/*_sqlMap.xml";
    private static String location;
    private Exception e;

    @SuppressWarnings("unused")
    private void setLocation(String location) {
        SqlSource.location = location;
    }

    private static boolean loaded = false;
    private Map<String, StatementDomInfo> cacheHoler = new HashMap<String, StatementDomInfo>();

    private Map<String, StatementDomInfo> getCacheHoler() {
        return this.cacheHoler;
    }

    private static SqlSource instance = new SqlSource();

    private SqlSource() {}

    public static SqlSource getInstance() {
        return instance;
    }

    public StatementDomInfo getStatementDomInfo(String statementId) {
        if (!loaded) {
            loadMapperResouece(null);
        }
        if (e != null) {
            throw new RuntimeException("SQL配置文件解析失败->", e);
        }
        return cacheHoler.get(statementId);
    }

    /**
     * 资源载入
     * 
     * @param location
     * @param cacheHoler
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map<String, StatementDomInfo> loadMapperResouece(String location) {
        Map<String, StatementDomInfo> cacheStatHoler = SqlSource.getInstance().getCacheHoler();
        if (loaded) {
            return cacheStatHoler;
        }
        try {
            ResourcePatternResolver resourceLoader = new PathMatchingResourcePatternResolver();
            if (StringUtils.isEmpty(location)) {
                if (StringUtils.isEmpty(SqlSource.location)) {
                    location = defauleLocation;
                } else {
                    location = SqlSource.location;
                }
            }
            Resource[] fs = resourceLoader.getResources("classpath*:" + location);
            for (Resource f : fs) {
                SAXReader reader = new SAXReader();
                reader.setEncoding("UTF-8");
                Document doc = reader.read(new InputStreamReader(f.getInputStream(), "UTF-8"));
                Element root = doc.getRootElement();
                String namespace = root.attributeValue(TagAttribute.NAMESPACE.value());
                List<Element> elements = root.elements();
                if (!CollectionUtils.isEmpty(elements)) {
                    for (Element el : elements) {
                        String StatementId = namespace + "." + el.attributeValue(TagAttribute.ID.value());
                        StatementDomInfo mstat = cacheStatHoler.get(StatementId);
                        if (mstat == null) {
                            String name = el.getName();
                            SqlCommandType sqlCommandType = SqlCommandType.SELECT;
                            if (SqlCommandType.UPDATE.value().equals(name)) {
                                sqlCommandType = SqlCommandType.SELECT;
                            } else if (SqlCommandType.DELETE.value().equals(name)) {
                                sqlCommandType = SqlCommandType.DELETE;
                            } else if (SqlCommandType.INSERT.value().equals(name)) {
                                sqlCommandType = SqlCommandType.INSERT;
                            }
                            StatementDomInfo stat = new StatementDomInfo(StatementId, sqlCommandType, el);

                            List<Element> subElements = el.elements();
                            boolean isDynamicSql = true;
                            if (CollectionUtils.isEmpty(subElements)) {
                                isDynamicSql = false;
                                String sql = el.getTextTrim();
                                stat.setStaticStatement(sql);
                            }
                            String tagName = el.attributeValue(TagAttribute.NAME.value());
                            if (StringUtils.isNotEmpty(tagName)) {
                                stat.setName(tagName);
                            }
                            System.out.println("StatementId->" + StatementId + ",Name->" + tagName);
                            stat.setDynamicStatementFleg(isDynamicSql);
                            cacheStatHoler.put(StatementId, stat);
                        } else {
                            throw new RuntimeException("sqlMap定义重复->" + StatementId);
                        }
                    }
                }
            }
        } catch (Exception e) {
            this.e = e;
        }
        loaded = true;
        return cacheStatHoler;
    }
}
