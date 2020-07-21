package cn.lsl.data.exchange.syncclient.service.impl;

import cn.lsl.data.exchange.syncclient.netty.NettyClient;
import cn.lsl.data.exchange.syncclient.properties.ReceiveProperties;
import cn.lsl.data.exchange.syncclient.repository.DataSourceRepository;
import cn.lsl.data.exchange.syncclient.service.SendMessageService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@Service
public class SendMessageServiceImpl implements SendMessageService{

    private Logger logger = LoggerFactory.getLogger(SendMessageServiceImpl.class);
    private Map<String,Map<String,String>> tablesCache;
    private Gson gson = new Gson();

    @Autowired
    private ReceiveProperties properties;

    @Autowired
    private DataSourceRepository dataSourceRepository;


    @Autowired
    private NettyClient client;

    //向服务端发送数据
    public void send(String startTime,String endTime) throws IOException {
        //new NettyClient(properties.getIp(),Integer.valueOf(properties.getPort()));
        Map<String,Map<String,String>> tablesMap = getTablesMap();
        //查询数据
        String resultJson = dataSourceRepository.getData(tablesMap,startTime,endTime);
        System.out.println(resultJson.length());
        byte[] gzip = resultJson.getBytes("UTF-8");
        String encode = new BASE64Encoder().encode(gzip);
        client.run(properties.getIp(),Integer.valueOf(properties.getPort()),encode);
    }

    public Map<String,Map<String,String>> getTablesMap(){

//        if(tablesCache != null){
//            return tablesCache;
//        }

        //分割表名
        String[] tables = properties.getTables().split(",");
        //#分割同步字段
        String[] columns = properties.getColumns().split("#");
        //-为占位符， #分割条件
        String[] conditions = properties.getConditions().replace("-"," ").split("#");

        //分割条件查询字段
        String[] syncColumns = properties.getSyncColumn().split(",");

        if(tables.length != columns.length){
            logger.error("表的数量与字段数量不相同");
            return null;
        }

        Map<String,Map<String,String>> map = new HashMap<String,Map<String,String>>();
        for(int i = 0 ; i < tables.length; i++){
            Map<String,String> column = new HashMap<>();
            column.put(columns[i],conditions[i]);
            column.put("syncColumn",syncColumns[i]);
            map.put(tables[i],column);
        }

        tablesCache = map;
        return map;
    }
}
