package cn.lsl.data.exchange.syncclient.repository;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class DataSourceRepository {

    private Logger logger = LoggerFactory.getLogger(DataSourceRepository.class);

    private List<String> splitColumns;
    @Autowired
    private DruidDataSource druidDataSource;

    private Gson gson = new Gson();

    //传入一个(tableName,columns)结构的map
    public String getData(Map<String,Map<String,String>> tables,String startTime,String endTime) throws UnsupportedEncodingException {

        if(tables.isEmpty()){
            logger.info("需要同步的表为空");
            throw new NullPointerException("需要同步的表为空");
        }

        Map<String,Map<String,List<String>>> resultMap = new HashMap<>();

        for(String key : tables.keySet()){
            Map<String,String> value = tables.get(key);
            //检查value
            Map<String,List<String>> resultData = new HashMap<>();
            //获取根据那个字段同步
            String syncColumn = value.get("syncColumn");
            value.remove("syncColumn");
            //储存原字段
            StringBuffer columnsOrgin =  new StringBuffer();
            //储存映射字段
            StringBuffer columnsMapping = new StringBuffer();
            for(String columnKey : value.keySet()) {

                List<String> resultList = null;

                if (null == columnKey || columnKey.isEmpty()) {
                    logger.info("{}需要同步的字段为空", key);
                    throw new NullPointerException(key + "需要同步的字段为空");

                } else if (columnKey.contains(" as ") || columnKey.contains(" AS ")) { //判断字段是否有别名
                    String[] column = columnKey.split(",");
                    //循环判断每个字段
                    for (int i = 0; i < column.length; i++) {
                        if (column[i].contains(" as ") || column[i].contains(" AS ")) {
                            //将列名与别名分开
                            String[] mapping = column[i].split(" ");
                            columnsOrgin.append(mapping[0]).append(",");
                            columnsMapping.append(mapping[2]).append(",");
                        } else {
                            //无别名
                            columnsOrgin.append(column[i]).append(",");
                            columnsMapping.append(column[i]).append(",");
                        }
                    }

                }else{
                    columnsOrgin.append(columnKey).append(",");
                    columnsMapping.append(columnKey).append(",");
                }

                //查看表名是否有映射
                if (key.contains(" as ") || key.contains(" AS ")) {
                    String[] keys = key.split(" ");
                    if (keys.length != 3) {
                        //报错

                    } else {
                        //根据原字段查数据
                        resultList = queryData(keys[0], columnsOrgin.toString().substring(0, columnsOrgin.lastIndexOf(",")),value.get(columnKey),startTime,endTime,syncColumn);
                        //保存映射字段与 根据原字段查出得数据
                        resultData.put(columnsMapping.toString().substring(0, columnsMapping.lastIndexOf(",")), resultList);
                        //保存映射表名与 (映射字段，数据)
                        resultMap.put(keys[2], resultData);
                    }
                } else {
                    //无别名
                    resultList = queryData(key,columnsOrgin.toString().substring(0, columnsOrgin.lastIndexOf(",")),value.get(columnKey),startTime,endTime,syncColumn);

                    resultData.put(columnsMapping.toString().substring(0, columnsMapping.lastIndexOf(",")), resultList);
                    resultMap.put(key, resultData);
                }
            }
            //将那个字段给加上
            value.put("syncColumn",syncColumn);
        }

        String result = gson.toJson(resultMap);
        return result;
    }

    public List<String> queryData( String table,String columns,String condition,String startTime,String endTime,String syncColumn) {


        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select " + columns + " from " + table;


        if(!startTime.isEmpty() && (null != condition && !condition.trim().equals(""))){
            sql += " where "+syncColumn +" >= " + startTime + " and " + condition;
            if(!endTime.isEmpty()){
                sql += " and "+syncColumn +" < " + endTime;
            }
        }else if(!startTime.isEmpty()){
            sql += " where "+syncColumn +" >= " + startTime;
            if(!endTime.isEmpty()){
                sql += " and "+syncColumn +" < " + endTime;
            }
        }else if(null != condition && !condition.equals(" ")){
            sql += " where " + condition;
        }


        logger.info("{}查询数据sql:{}",table,sql);
        String[] column = columns.split(",");
        String[] str = new String[20];
        List<String> resultData = new ArrayList<>(100);
        StringBuffer sb = new StringBuffer();
        try {
            connection = druidDataSource.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                sb.setLength(0);
                //根据查询的字段数循环
                for(int i = 1; i <= column.length;i++) {
                    String encoded = rs.getString(i).replace(",","###||");
                    if(i == column.length){
                        sb.append(encoded);
                    }else {
                        sb.append(encoded).append(",");
                    }
                }

                resultData.add(sb.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
           if(rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultData;
    }


}
