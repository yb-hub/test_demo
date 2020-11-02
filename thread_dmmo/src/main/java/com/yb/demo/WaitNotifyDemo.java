package com.yb.demo;

/**
 * @Auther: Yang
 * @Date: 2020/07/11 10:20
 * @Description:
 */
public class WaitNotifyDemo {
    static boolean hasYan = false;


    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        WaitNotifyDemo waitNotifyDemo = new WaitNotifyDemo();
        Thread A = new Thread(() -> {
            waitNotifyDemo.work(lock);
        });

        Thread A1 = new Thread(() -> {
            waitNotifyDemo.work(lock);
        });

        Thread A2 = new Thread(() -> {
            waitNotifyDemo.work(lock);
        });

        Thread C = new Thread(() -> {
            synchronized (lock) {
                System.out.println("先送外卖或者先送烟");
                //送烟
                hasYan = true;
                lock.notifyAll();
                System.out.println("送烟/外卖完毕");
            }

        });

        A.start();
        A1.start();
        A2.start();
        Thread.sleep(1000);
        C.start();
    }

    private void work(Object lock) {
        synchronized (lock) {
            System.out.println("我要抽个烟，然后开始干活");
            //等待抽烟
            try {
                while(!hasYan){
                    lock.wait();
                }
                if (hasYan) {
                    System.out.println(Thread.currentThread() + "收到烟了，抽烟完毕，开始干活");
                    hasYan = false;
                }else{
                    System.out.println(Thread.currentThread()+"没烟，干不了活");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
