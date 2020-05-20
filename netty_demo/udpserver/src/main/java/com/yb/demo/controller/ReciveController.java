package com.yb.demo.controller;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author yb
 * @description
 * @data 2020/5/15
 */
@Component
public class ReciveController implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=========server start==========");
        //创建datagram的socket
        DatagramSocket datagramSocket = new DatagramSocket(8080);
        //获取数据包
        while (true){
            System.out.println("==========正在获取==========");
            byte[] bytes = new byte[1024];
            DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
            //获取数据流
            datagramSocket.receive(datagramPacket);
            String hostAddress = datagramPacket.getAddress().getHostAddress();
            byte[] data = datagramPacket.getData();
            String dataStr = new String(data, 0, datagramPacket.getLength());
            System.out.println(hostAddress+":"+dataStr);
        }
    }
}
