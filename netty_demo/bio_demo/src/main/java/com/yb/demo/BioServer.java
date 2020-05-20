package com.yb.demo;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author yb
 * @description bio服务器端
 * @data 2020/5/20
 */
public class BioServer {
    public static void main(String[] args) throws IOException {
        //创建一个socket
        ServerSocket serverSocket = new ServerSocket(8001);
        //使用socket接收数据
        while(true){
            System.out.println("等待客户端连接。。。");
            //获取客户端的socket
            Socket clientSocket = serverSocket.accept();
            System.out.println("客户端连接成功。。。");
            //每个客户端都创建一个线程 使用clientSocket读取数据
            new Thread(()->{
                try {
                    handler(clientSocket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    public static void handler(Socket clientSocket) throws IOException {
        InputStream inputStream = clientSocket.getInputStream();
        byte[] bytes = new byte[1024];
        do{
            int readNumber = inputStream.read(bytes);
            if(readNumber == -1){
                break;
            }else{
                //打印一下读取的数据
                System.out.println("当前线程："+Thread.currentThread()+"收到数据："+new String(bytes));
            }
        }while (true);
        inputStream.close();
        clientSocket.close();
    }
}
