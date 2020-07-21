package cn.lsl.data.exchange.syncclient.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class NettyClient {

//    private String ip;
//
//    private int port;
//
//    private boolean stop = false;
//
//    public NettyClient(String ip, int port) {
//        this.ip = ip;
//        this.port = port;
//    }
    @Autowired
    private ClientIniterHandler clientIniterHandler;

    public void run(String ip,int port,String message) throws IOException {
        //设置一个多线程循环器
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        //启动附注类
        Bootstrap bootstrap = new Bootstrap();
        try {
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline p = ch.pipeline();
                        p.addLast(new StringDecoder());
                        p.addLast(new StringEncoder());
                        p.addLast(clientIniterHandler);
                    }
                });
//            //连接服务
//            ChannelFuture future = bootstrap.connect(ip, port).sync();
//                //向服务端发送内容
//                if (!message.isEmpty()) {
//                    future.channel().writeAndFlush(message);
//                }
            ChannelFuture future = bootstrap.connect(ip, port).sync();

            if(!message.isEmpty()){
                future.channel().writeAndFlush(message);
            }
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws IOException {
            new NettyClient().run("127.0.0.1",8072,"ooooo");
    }
}
