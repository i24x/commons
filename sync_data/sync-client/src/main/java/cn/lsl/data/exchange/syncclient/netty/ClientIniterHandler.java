package cn.lsl.data.exchange.syncclient.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@ChannelHandler.Sharable
public class ClientIniterHandler extends ChannelInboundHandlerAdapter {
    private Logger logger = LoggerFactory.getLogger(ClientIniterHandler.class);
    private boolean ok ;

    private StringBuffer sb = new StringBuffer();

    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object msg) {
        logger.info("接收到客户端消息：{}",msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Channel channel = ctx.channel();
        if(channel.isActive()) {
            channel.close();
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        logger.info("数据接收完毕");
        ctx.flush();    }
}
