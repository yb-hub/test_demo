package com.yb.demo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @author yb
 * @description 群聊客户端
 * @data 2020/5/28
 */
public class GroupChatClient {
    private Selector selector;
    private SocketChannel socketChannel;
    private String username;
    private final  String HOST = "127.0.0.1";
    private final  int PORT = 8888;

    public GroupChatClient(){
        try {
            //创建一个selector
            selector = Selector.open();
            //创建一个socketChannel，绑定服务器ip地址
            socketChannel = SocketChannel.open(new InetSocketAddress(HOST,PORT));
            socketChannel.configureBlocking(false);
            //注册到selector,绑定监听 客户端可读 事件
            socketChannel.register(selector, SelectionKey.OP_READ);
            username = socketChannel.getLocalAddress().toString().substring(1);
            System.out.println(username + "创建成功");
            //创建一个线程来获取服务器端返回的消息
//            new Thread(()->{
//                reciveMessage();
//            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void reciveMessage() {
        try {
                int count = selector.select();
                if(count > 0){
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while(iterator.hasNext()){
                        SelectionKey selectionKey = iterator.next();
                        if(selectionKey.isReadable()){
                            SocketChannel channel = (SocketChannel) selectionKey.channel();
                            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                            channel.read(byteBuffer);
                            System.out.println(new String(byteBuffer.array()));
                        }
                    }
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void sendMessage(String message){
        //使用channel将数据写入
        try {
            socketChannel.write(ByteBuffer.wrap(message.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        GroupChatClient groupChatClient = new GroupChatClient();
        GroupChatClient groupChatClient1 = new GroupChatClient();


        //启动一个线程, 每个3秒，读取从服务器发送数据
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    groupChatClient1.reciveMessage();
                    try {
                        sleep(3000);
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

//        groupChatClient.sendMessage("hello world");
//        groupChatClient1.sendMessage("你好 世界");
//
        //发送数据给服务器端
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            groupChatClient.sendMessage(s);
//            if(s.length()>10){
//                groupChatClient.sendMessage(s);
//            }else{
//                groupChatClient1.sendMessage(s);
//            }
        }
    }
}
