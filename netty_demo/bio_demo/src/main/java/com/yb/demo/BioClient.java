package com.yb.demo;

import lombok.extern.slf4j.Slf4j;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.net.Socket;

/**
 * @author yb
 * @description 客户端
 * @data 2020/10/28
 */
@Slf4j
public class BioClient {
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            createClient("127.0.0.1",8001);
        }
    }

    public static void createClient(String host,int port){
        log.info("开始连接服务器，host={},port={}",host,port);
        try {
            Socket socket = new Socket(host, port);
            log.info("连接服务器成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
