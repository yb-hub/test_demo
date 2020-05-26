package com.yb.demo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author yb
 * @description nio客户端
 * @data 2020/5/21
 */
public class NioClient {
    public static void main(String[] args) throws IOException {
        //创建一个网络通道
        SocketChannel socketChannel = SocketChannel.open();
        //设置非阻塞
        socketChannel.configureBlocking(false);
        //连接服务器
        if(!socketChannel.connect(new InetSocketAddress("127.0.0.1",8001))){
            System.out.println("连接不成功。。。");
            while(!socketChannel.finishConnect()){
                System.out.println("随便干点别的。。。");
            }
        }
        String str = "hello world";
        ByteBuffer byteBuffer = ByteBuffer.wrap(str.getBytes());
        //发送数据
        socketChannel.write(byteBuffer);
        System.in.read();
    }
}
