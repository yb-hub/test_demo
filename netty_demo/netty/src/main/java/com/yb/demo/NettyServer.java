package com.yb.demo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author yb
 * @description
 * @data 2020/6/4
 */
public class NettyServer {
    public static void main(String[] args) throws InterruptedException {
        //创建bossgroup和workergroup
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        //创建服务器的启动对象，并配置参数
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup) //设置两个线程组
                .channel(NioServerSocketChannel.class) //使用nioserverscoketchannel作为服务器的通信实现
                .option(ChannelOption.SO_BACKLOG, 128) //设置线程队列的连接个数
                .childOption(ChannelOption.SO_KEEPALIVE, true) //设置保持活动连接状态
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        System.out.println("客户socketchannel hashcode=" + ch.hashCode()); //可以使用一个集合管理 SocketChannel， 再推送消息时，可以将业务加入到各个channel 对应的 NIOEventLoop 的 taskQueue 或者 scheduleTaskQueue
                        ch.pipeline().addLast(new NettyServerHandler());
                    }
                }); //创建一个通道处理对象
        //绑定端口,并同步生成一个channelfuture对象
        //启动服务器
        ChannelFuture channelFuture = serverBootstrap.bind(8888).sync();
        System.out.println(".....服务器 is ready...");
        //对关闭通道进行监听
        channelFuture.channel().closeFuture().sync();
    }
}
