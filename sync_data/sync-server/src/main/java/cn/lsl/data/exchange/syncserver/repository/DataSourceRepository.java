package cn.lsl.data.exchange.syncserver.repository;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DataSourceRepository {

    private Logger logger = LoggerFactory.getLogger(DataSourceRepository.class);

    @Autowired
    private DruidDataSource druidDataSource;
    private Gson gson = new Gson();

    public void saveData(String table, String columns,List<String> datas){

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = druidDataSource.getConnection();

            int columnsSize = columns.split(",").length;
            StringBuffer sqlBuffer = new StringBuffer();
            sqlBuffer.append("insert into ").append(table).append(" (").append(columns).append(") value ");

            for(int i = 0 ; i< datas.size();i++) {
                String data = gson.toJson(datas.get(i));

                if (i == (datas.size() - 1) || (i + 1) % 1000 == 0) {

                    data = data.replace(",", "\",\"");
                    data = data.replace("###||",",");
                    sqlBuffer.append("(").append(data).append(")");
                    preparedStatement = connection.prepareStatement(sqlBuffer.toString());
                    preparedStatement.execute();
                    sqlBuffer.setLength(0);
                    sqlBuffer.append("insert into ").append(table).append(" (").append(columns).append(") value ");
                } else {
                    sqlBuffer.append("(").append(data.replace(",", "\",\"")).append("),");
                }

            }
            logger.info("{}表数据同步成功",table);
        } catch (SQLException e) {
            logger.info("{}表数据同步失败",table);
            e.printStackTrace();
        }finally{
            try {
                if(preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if(connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
