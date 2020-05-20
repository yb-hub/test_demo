package com.yb.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.*;

/**
 * @author yb
 * @description
 * @data 2020/5/15
 */
@RestController
public class SendController {
    @PostMapping("/send")
    public String sendMessage(String message) throws IOException {
        //创建一个datagram报文的socket
        DatagramSocket ds = new DatagramSocket();
        //创建datagram报文
        byte[] data = message.getBytes();
        InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
        DatagramPacket datagramPacket = new DatagramPacket(data, data.length, inetAddress, 8080);
        //发送
        ds.send(datagramPacket);
        //关闭
        ds.close();
        return "success";
    }
}
