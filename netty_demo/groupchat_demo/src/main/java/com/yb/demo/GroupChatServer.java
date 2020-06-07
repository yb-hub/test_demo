package com.yb.demo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author yb
 * @description 服务器端
 * @data 2020/5/27
 */
public class GroupChatServer {
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private static final int PORT = 8888;

    public GroupChatServer() {
        try {
            //获取一个selector，用于轮询channel
            this.selector = Selector.open();
            //创建一个serversocketChannel，并设置为非阻塞
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress(PORT));
            //把channel注册到selector中,并设置监听 客户端接入 的事件
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //开始监听
    public void listen() {
        while (true) {
            try {
                //获取事件被触发的channel的数量，如果大于0，则说明有事件被触发
                int count = selector.select();
                if (count > 0) {
                    //获取所有的selectionKey
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
                    while (keyIterator.hasNext()) {
                        SelectionKey selectionKey = keyIterator.next();
                        //判断事件类型
                        if (selectionKey.isAcceptable()) {
                            //如果是 客户端接入事件 就从serverSocketChannel中获取一个channel,并注册到selector上,设置监听 通道可读入（客户端写入） 事件
                            SocketChannel socketChannel = serverSocketChannel.accept();
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector, SelectionKey.OP_READ);
                            System.out.println(socketChannel.getRemoteAddress() + "上线了");
                        }
                        if (selectionKey.isReadable()) {
                            // 读取客户端写入的数据
                            SocketChannel channel = (SocketChannel) selectionKey.channel();
                            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                            channel.read(byteBuffer);
                            System.out.println("接收到数据：" + new String(byteBuffer.array()));
                            // 群发给其他客户端
                            sentMessage2Others(new String(byteBuffer.array()), channel);
                        }
                        //当前的key 删除，防止重复处理
                        keyIterator.remove();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sentMessage2Others(String message, SocketChannel self) {
        //遍历selectionKey获取所有客户端通道
        //Set<SelectionKey> selectionKeys = selector.selectedKeys();
        //for (SelectionKey selectionKey : selectionKeys) {
        Set<SelectionKey> keys = selector.keys();
        for (SelectionKey key : keys) {
        SelectableChannel channel = key.channel();
            //如果是channal类型是socketChannel并且不是本身的客户端，就将数据写入
            if (channel instanceof SocketChannel && channel != self) {
                SocketChannel socketChannel = (SocketChannel) channel;
                try {
                    message = self.getRemoteAddress().toString().substring(1) + "说:" + message;
                    //System.out.println(message);
                    socketChannel.write(ByteBuffer.wrap(message.getBytes()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        GroupChatServer groupChatServer = new GroupChatServer();
        groupChatServer.listen();
    }
}
