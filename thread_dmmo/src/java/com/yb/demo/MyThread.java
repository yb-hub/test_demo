package com.yb.demo;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author yb
 * @description
 * @data 2021/10/18
 */
public class MyThread implements Runnable{

    private static final Logger log = LoggerFactory.getLogger(MyThread.class);

    private String name;
    private String prve;
    private String self;

    public static int count = 1;

    public MyThread(String name, String prve, String self) {
        this.name = name;
        this.prve = prve;
        this.self = self;
    }

    @Override
    public void run() {
        while(true){
            synchronized (prve){
                synchronized (self){
                    for (int i = 0; i < 3; i++) {
                        if(count <= 100){
                            System.out.println(name+":"+count++);
                        }else{
                            break;
                        }
                    }
                    self.notifyAll();
                }
                try {
                    prve.wait();
                } catch (InterruptedException e) {
                    log.info("异常中断");
                }
            }
        }

    }

    //    @Override
//    public void run() {
//        while(true){
//            synchronized (prve){
//                synchronized (self){
//                    log.info(name);
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    self.notifyAll();
//                }
//
//                try {
//                    prve.wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
}
