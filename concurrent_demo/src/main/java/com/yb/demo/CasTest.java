package com.yb.demo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author yb
 * @description cas测试（compare and swap比较并交换）
 * @data 2021/11/17
 */
@Slf4j
public class CasTest {

    public static void main(String[] args) {
        AtomicInteger number = new AtomicInteger();
        //比较并设置
        boolean isSuccess = number.compareAndSet(1, 100);
        System.out.println(isSuccess);
        //获取并自增
        int newNumber = number.getAndIncrement();
        System.out.println(newNumber);

        //原子对象引用
        AtomicReference<User> atomicReference = new AtomicReference<>();
        User user = new User("yang1",18);
        User user2 = new User("yang2", 19);
        atomicReference.set(user);
        new Thread(()->{
            //aba
            atomicReference.compareAndSet(user, user2);
            atomicReference.compareAndSet(user2, user);
        }).start();

        new Thread(()->{
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                log.info("异常中断");
            }
            boolean result = atomicReference.compareAndSet(user, user2);
            log.info("result={}",result);
        }).start();

        //时间戳原子引用 通过添加类似版本号解决aba问题
        AtomicStampedReference<User> stampedReference = new AtomicStampedReference<User>(user,1);
        new Thread(()->{
            stampedReference.compareAndSet(user,user2,stampedReference.getStamp(),stampedReference.getStamp()+1);
            stampedReference.compareAndSet(user2,user,stampedReference.getStamp(),stampedReference.getStamp()+1);
        }).start();

        new Thread(()->{
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                log.info("异常中断");
            }
            int stamp = stampedReference.getStamp();
            log.info("stamp={}",stamp);
            boolean result = stampedReference.compareAndSet(user, user2, 1, stamp);
            log.info("result={}",result);
        }).start();


    }
}
