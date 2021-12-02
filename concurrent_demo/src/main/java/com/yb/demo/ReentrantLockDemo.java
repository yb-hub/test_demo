package com.yb.demo;

import lombok.extern.slf4j.Slf4j;
import sun.rmi.runtime.Log;

import javax.xml.bind.SchemaOutputResolver;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
class ReentrantLockTest{
    private ReentrantLock lock = new ReentrantLock();
    private HashMap<Integer,Object> map = new HashMap<>();

    public void put(Integer key,Object value){
        try{
            lock.lock();
            log.info(Thread.currentThread().getName()+"开始 put");
            map.put(key,value);
            log.info(Thread.currentThread().getName()+"完成 put");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public Object get(Integer key){
        try{
            lock.lock();
            log.info(Thread.currentThread().getName()+"开始 get");
            Object result = map.get(key);
            log.info(Thread.currentThread().getName()+"完成 get");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            lock.unlock();
        }
    }
}

@Slf4j
class ReentrantReadWriteLockTest{
    //private ReentrantLock lock = new ReentrantLock();
    private HashMap<Integer,Object> map = new HashMap<>();

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void put(Integer key,Object value){
        try{
            lock.writeLock().lock();
            log.info(Thread.currentThread().getName()+"开始 put");
            map.put(key,value);
            log.info(Thread.currentThread().getName()+"完成 put");
            //Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }

    public Object get(Integer key){
        try{
            lock.readLock().lock();
            log.info(Thread.currentThread().getName()+"开始 get");
            Object result = map.get(key);
            Thread.sleep(100);
            log.info(Thread.currentThread().getName()+"完成 get");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            lock.readLock().unlock();
        }
    }
}

/**
 * @Auther: Yang
 * @Date: 2020/07/19 22:35
 * @Description:
 */
public class ReentrantLockDemo {
    public static void main(String[] args) throws InterruptedException {
//        ReentrantLock lock = new ReentrantLock();
//
//        Thread t1 = new Thread(() -> {
//            try {
//                lock.lockInterruptibly();
//            } catch (InterruptedException e) {
//                System.out.println("t1线程被打断了");
//                e.printStackTrace();
//            }
//        }, "t1");
//
//        //先让主线程获取锁
//        lock.lock();
//        //然后启动t1线程，让它去竞争锁
//        t1.start();
//        Thread.sleep(1000);
//        //在竞争锁的过程中中断t1线程
//        t1.interrupt();
        //reentrantlockTest();
        //reentrantReadWriteLockTest();

        //不同线程lock unlock
        lockAndUnlockTest();
    }

    private static void lockAndUnlockTest() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        //new Thread(lock::lock).start();
        try {
            lock.lock();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private static void reentrantReadWriteLockTest() {
        ReentrantReadWriteLockTest reentrantLockTest = new ReentrantReadWriteLockTest();

        for (int i = 0; i < 50; i++) {
            int finalI = i;
            new Thread(()->{
                reentrantLockTest.put(finalI,finalI);
            }).start();
        }

        for (int i = 0; i < 50; i++) {
            int finalI = i;
            new Thread(()->{
                reentrantLockTest.get(finalI);
            }).start();
        }
    }

    private static void reentrantlockTest() {

        ReentrantLockTest reentrantLockTest = new ReentrantLockTest();

        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(()->{
                reentrantLockTest.put(finalI,finalI);
            }).start();
        }

        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(()->{
                reentrantLockTest.get(finalI);
            }).start();
        }
    }
}
