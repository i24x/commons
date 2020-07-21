package cn.lsl.data.exchange.syncserver.netty;

import cn.lsl.data.exchange.syncserver.repository.DataSourceRepository;
import com.google.gson.Gson;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;

import java.util.List;
import java.util.Map;

@Component
@ChannelHandler.Sharable
public class ClientIniterHandler extends ChannelInboundHandlerAdapter {

    private Logger logger = LoggerFactory.getLogger(ClientIniterHandler.class);
    Gson gson = new Gson();

    @Autowired
    private DataSourceRepository dataSourceRepository;

    private boolean ok ;

    private StringBuffer sb = new StringBuffer();

    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object msg) {
        if (filterIp(channelHandlerContext)){
            return;
        }
        ok = false;
        sb.append((String)msg);
    }

    public boolean filterIp(ChannelHandlerContext channelHandlerContext){
        boolean isFilter = false;
        Channel channel = channelHandlerContext.channel();
        String remoteIp = channel.remoteAddress().toString();
        logger.info("channelRead客户端ip:"+remoteIp);
        if(remoteIp.indexOf("60.164.220.247")==-1){
            isFilter = true;
            System.out.println("过滤掉地址："+remoteIp);
        }
        return isFilter;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if(filterIp(ctx)){
            return;
        }
        Channel channel = ctx.channel();
        if(channel.isActive()) {
            channel.close();
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        if(filterIp(ctx)){
            return;
        }
        try{
                Channel channel = ctx.channel();
                logger.info("channelReadComplete客户端ip:"+channel.remoteAddress());
                System.out.println(sb.toString().length());

                byte[] decodeOut = new BASE64Decoder().decodeBuffer(sb.toString());
                System.out.println(new String(decodeOut,"UTF-8"));

                byte[] decode = new BASE64Decoder().decodeBuffer(sb.toString());
                logger.info("解密完成");
                String result = new String(decode,"UTF-8");
                Map<String, Map<String, List<String>>> dataMap = gson.fromJson(result, Map.class);
                sb.setLength(0);
                if (!dataMap.isEmpty()) {
                    for (String table : dataMap.keySet()) {
                        logger.info("获取列名");
                        Map<String, List<String>> columns = gson.fromJson(gson.toJson(dataMap.get(table)), Map.class);
                        for (String column : columns.keySet()) {
                            logger.info("获取数据");
                            List<String> data = gson.fromJson(gson.toJson(columns.get(column)), List.class);
                            logger.info("数据共{}条", data.size());
                            if (data.isEmpty()) {
                                continue;
                            }
                            logger.info("开始保存数据");
                            dataSourceRepository.saveData(table, column, data);
                            logger.info("保存数据结束");
                        }
                    }
                }
            ctx.writeAndFlush("数据保存完毕");
            logger.info("数据保存完毕");
                ctx.close();
        }catch (Exception e){
            logger.info("继续读取数据");
        }
    }
}
