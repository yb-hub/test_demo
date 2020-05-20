package com.yb.demo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author yb
 * @description nio服务器
 * @data 2020/5/20
 */
public class NioServer {
    public static void main(String[] args) throws IOException {
        //创建一个ServerSocketChannel,类似ServerSocket
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //设置ServerSocketChannel属性,监听8001端口
        serverSocketChannel.socket().bind(new InetSocketAddress(8001));
        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        //创建一个selector
        Selector selector = Selector.open();
        //把serverSocketChannel注册在selector上,关心事件为OP_ACCEPT
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("注册后的selecttionKey的数量="+selector.selectedKeys().size());

        //循环等待客户端连接
        while(true){
            if(selector.select(1000) == 0){
                System.out.println("一秒内暂无客户端连接。。。");
                continue;
            }
            //如果大于0，即说明有客户端连接。
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            System.out.println("selectionKeys数量："+selectionKeys.size());

            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while(iterator.hasNext()){
                //获取selecttionKey
                SelectionKey selectionKey = iterator.next();
                //根据key 相应的通道发生的事件做相应的处理
                if(selectionKey.isAcceptable()){
                    //如果是OP_ACCEPT，则说明有客户端连接,生成一个socketChannel,类似bio中的socket
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    //设置为非阻塞
                    socketChannel.configureBlocking(false);
                    //把socketChannel注册到selector上,关注事件是OP_READ,并关联一个buffer
                    socketChannel.register(selector,SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                    System.out.println("客户端连接后，selectionKeys数量："+selectionKeys.size());
                }
                if(selectionKey.isReadable()){
                    //通过key反向获取到对应的channel
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    //获取到关联的buffer
                    ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
                    channel.read(buffer);
                    System.out.println("收到的消息："+new String(buffer.array()));
                }
                //手动移除当前的selectionKey
                iterator.remove();
            }
        }
    }
}
