package com.lsl.commons.lang.utils.db.sql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;
import org.dom4j.tree.DefaultCDATA;
import org.dom4j.tree.DefaultText;
import org.springframework.util.CollectionUtils;

/**
 * 1.支持 isEmpty isNotEmpty iterate标签解析 <br>
 * 2.变量占位符使用${xx} 数组使用 ${xx[]} <br>
 * 3.支持属性property prepend open conjunction close <br>
 * 4.根据需要自扩展功能
 */
public class StatementParser {
    private static final String SPACE_CHAR = " ";
    private static final String DEFAULT_CONJUN = ",";

    /**
     * 解析动态标签
     * 
     * @param params
     * @param el
     * @return
     */
    @SuppressWarnings("unchecked")
    public static StringBuffer parseDynamicTags(Map<String, Object> params, Element el) {
        StringBuffer sql = new StringBuffer();
        Iterator<Object> nodeIterator = el.nodeIterator();
        while (nodeIterator.hasNext()) {
            Object cont = nodeIterator.next();
            if (cont instanceof Element) {
                Element subEle = (Element)cont;
                String tagName = subEle.getName();
                String propertiesKey = subEle.attributeValue(TagAttribute.PROPERTY.value());
                Object valueObj = params.get(propertiesKey);
                // System.out.println("tagName->"+tagName);
                if (StringUtils.equals(tagName, TagType.isNotEmpty.value())) {
                    if (valueObj != null && valueObj instanceof List) {
                        List<?> values = (List<?>)valueObj;
                        if (values.size() > 0) {
                            sql.append(parseDynamicTags(params, subEle));
                        }
                    } else if (valueObj != null && valueObj instanceof String) {
                        if (StringUtils.isNotEmpty((String)valueObj)) {
                            sql.append(parseDynamicTags(params, subEle));
                        }
                    } else if (valueObj != null) {
                        sql.append(parseDynamicTags(params, subEle));
                    }
                } else if (StringUtils.equals(tagName, TagType.isEmpty.value())) {
                    if (valueObj == null) {
                        sql.append(parseDynamicTags(params, subEle));
                    } else if (valueObj instanceof List) {
                        List<?> values = (List<?>)valueObj;
                        if (values.size() == 0) {
                            sql.append(parseDynamicTags(params, subEle));
                        }
                    } else if (valueObj != null && valueObj instanceof String) {
                        if (StringUtils.isEmpty((String)valueObj)) {
                            sql.append(parseDynamicTags(params, subEle));
                        }
                    }
                } else if (StringUtils.equals(tagName, TagType.iterate.value())) {
                    String prepend = subEle.attributeValue(TagAttribute.PREPEND.value());
                    String open = subEle.attributeValue(TagAttribute.OPEN.value());
                    String conjunction = subEle.attributeValue(TagAttribute.CONJUNCTION.value());
                    String close = subEle.attributeValue(TagAttribute.CLOSE.value());
                    Object object = valueObj;
                    List<Object> list = null;
                    if (valueObj instanceof List) {
                        list = (List<Object>)object;
                    }
                    if (!CollectionUtils.isEmpty(subEle.elements())) {
                        throw new RuntimeException("标签<iterate>不允许内嵌标签使用");
                    }
                    StringBuffer iterateContent = StatementParser.parseIterateTag(prepend, open, conjunction, close,
                        list, subEle.getTextTrim(), null);
                    sql.append(iterateContent);
                }
            } else if (cont instanceof DefaultText) {
                DefaultText content = (DefaultText)cont;
                sql.append(trimAll(content.getText()));
            } else if (cont instanceof DefaultCDATA) {
                DefaultCDATA content = (DefaultCDATA)cont;
                sql.append(trimAll(content.getText()));
            } else {
                // 扩展解析类型处理
            }
            // }
        }
        return sql;
    }

    private static String trimAll(String s) {
        return s == null ? s : s.replaceAll("[\\r\\n\\t\\s]{1,}", StatementParser.SPACE_CHAR);
    }

    /**
     * 解析Iterate标签
     * 
     * @param prepend
     * @param open
     * @param conjun
     * @param close
     * @param list
     * @param content
     * @param p
     * @return
     */
    public static StringBuffer parseIterateTag(String prepend, String open, String conjun, String close, List<?> list,
        String content, Map<String, Object> p) {
        StringBuffer s = new StringBuffer();

        if (list == null || list.size() == 0) {
            throw new RuntimeException("迭代列表不可为空");
        }
        if (StringUtils.isEmpty(content)) {
            throw new RuntimeException("迭代内容不可为空");
        }
        if (StringUtils.isNotEmpty(prepend)) {
            s./*append(StatementParser.SPACE_CHAR).*/append(prepend);
        }
        if (StringUtils.isNotEmpty(open)) {

            s./*append(StatementParser.SPACE_CHAR).*/append(open);
        }
        for (Object v : list) {
            String iterateContent = content;
            if (!(v instanceof Integer)) {
                iterateContent = "'" + content + "'";
            }
            s.append(iterateContent);
            int start = s.indexOf("${");
            int end = s.indexOf("]}") + 2;
            if ((start > -1) && (end > start)) {
                s.replace(s.indexOf("${"), s.indexOf("]}") + 2, v.toString());
            }
            if (StringUtils.isNotEmpty(conjun)) {
                s.append(conjun);
            } else {
                s.append(StatementParser.DEFAULT_CONJUN);
            }
        }
        if (StringUtils.isNotEmpty(close)) {
            s.replace(s.length() - 1, s.length(), close);
        }
        return s;
    }

    /**
     * 解析${xxx}
     * 
     * @param sql
     * @param param
     * @return sql
     */
    public static String replaceContentPlaceHolder(String sql, Map<String, Object> param) {
        String regexp = "(\\$\\{).+?(\\})";
        Pattern p = Pattern.compile(regexp);
        Matcher m = p.matcher(sql);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            String pKey = m.group().trim();
            pKey = pKey.substring(2, pKey.length() - 1);
            Object object = param.get(pKey);
            if (object == null) {
                throw new RuntimeException("参数：" + pKey + "值未设置，sql->" + sql);
            }
            if (object instanceof String) {
                m.appendReplacement(sb, "'" + object.toString() + "'");
            } else {
                m.appendReplacement(sb, object.toString());
            }
        }
        m.appendTail(sb);
        return sb.toString();
    }

    public static String getParsedSQL(String statementId, Map<String, Object> param) {
        String sql = "";
        StatementDomInfo stat = SqlSource.getInstance().getStatementDomInfo(statementId);
        boolean isDynamicStatement = stat.isDynamicStatement();
        if (!isDynamicStatement) {
            sql = stat.getStaticStatement();
        } else {
            Element e = stat.getStatementDom();
            sql = StatementParser.parseDynamicTags(param, e).toString();
        }
        sql = StatementParser.replaceContentPlaceHolder(sql, param);
        System.out.println("StatementId->" + statementId + ",Name->" + stat.getName());
        return sql;
    }

    /**
     * 解析#{xxx}->?
     * 
     * @param name
     * @param param
     * @return
     */
    public static SqlBound getPreparedStatementSQL(String name, Map<String, Object> param) {
        String sql = StatementParser.getParsedSQL(name, param);
        LinkedHashMap<Integer, Object> paramMap = new LinkedHashMap<Integer, Object>();
        String regexp = "(\\#\\{).+?(\\})";
        Pattern p = Pattern.compile(regexp);
        Matcher m = p.matcher(sql);
        StringBuffer sb = new StringBuffer();
        Integer index = 1;
        while (m.find()) {
            String pKey = m.group().trim();
            pKey = pKey.substring(2, pKey.length() - 1);
            String v = (String)param.get(pKey);
            paramMap.put(index++, v);
            m.appendReplacement(sb, "?");
        }
        m.appendTail(sb);
        SqlBound pm = new SqlBound(sb.toString(), paramMap);
        return pm;
    }

    public static void main(String[] args) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("M1", "M1");
        params.put("M2", "M2");
        params.put("M3", "M3");
        params.put("M4", "M4");
        List<Object> list = new ArrayList<Object>();
        list.add("ABC123366");
        list.add("EFG456789");
        params.put("list", list);
        // params.put("list", null);
        params.put("cuid", "1212122");
        // System.out.println(StatementParser.getParsedSQL("TRAPH.queryTraphTextRoute", params));
        // System.out.println(StatementParser.getParsedSQL("TRAPH.queryTraphTextRoute", params));
        // System.out.println(StatementParser.getParsedSQL("TRAPH.findPtnPathBackPoints", params));
        System.out.println(StatementParser.getParsedSQL("TRAPH.queryMstptraphRoute", params));
        System.out.println(StatementParser.getParsedSQL("TRAPH.selectTest2", params));
        // System.out.println(StatementParser.getParsedSQL("TRAPH.findPtnPathPoints", params));
        // String sql = SqlHepler.getSql(new TraphDo() , new Traph(),"");

    }
}
