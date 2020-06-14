package com.yb.demo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author yb
 * @description
 * @data 2020/6/5
 */
public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
        //创建客户端的启动对象
        Bootstrap bootstrap = new Bootstrap();
        //设置客户端的相关配置
        bootstrap.group(new NioEventLoopGroup())  //设置事件循环组，监听服务器端事件？
                .channel(NioSocketChannel.class) //设置客户端的通信通道
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new NettyClientHandler());
                    }
                });
        //连接到服务器
        ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8888);
        System.out.println("客户端 ok...");
        //设置客户端关闭的监听事件
        channelFuture.channel().closeFuture().sync();
    }
}
