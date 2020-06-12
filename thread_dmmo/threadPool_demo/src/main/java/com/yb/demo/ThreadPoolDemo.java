package com.yb.demo;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * @author yb
 * @description
 * @data 2020/6/12
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        //threadPoolTest1();
        threadPoolTest2();
    }

    /**
     * 使用ThreadPoolExecutor手动创建线程池
     */
    private static void threadPoolTest2() {
        /**
         * corePoolSize:线程池中核心线程数的最大值
         * maximumPoolSize：线程池中能拥有最多线程数
         * keepAliveTime:表示空闲线程的存活时间
         * TimeUnit:表示keepAliveTime的单位
         * BlockingQueue:用于缓存任务的阻塞队列
         * 任务会先通过创建线程来执行，当线程数大于corePoolSize时，任务会加入到BlockingQueue中，如果队列也满了，就会创建线程，直到线程数量等于maximumPoolSize
         * 此时，如果还有任务，会执行拒绝策略（RejectedExecutionHandler ）
         * 有四种拒绝策略：
         * AbortPolicy	抛出RejectedExecutionException
         * DiscardPolicy	什么也不做，直接忽略
         * DiscardOldestPolicy	丢弃执行队列中最老的任务，尝试为当前提交的任务腾出位置
         * CallerRunsPolicy	直接由提交任务者执行这个任务
         */
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 100, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(20),new ThreadPoolExecutor.DiscardPolicy());
        for (int i = 0; i < 600; i++) {
            threadPoolExecutor.execute(() -> {
                int queueSize = threadPoolExecutor.getQueue().size();
                System.out.println("队列大小：" + queueSize);
                System.out.println("当前线程:" + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    /**
     * 使用Executors直接创建线程池,内部调用的是ThreadPoolExecutor,任务队列为链表，可能会造成OOM
     */
    private static void threadPoolTest1() {
        //可以使用Executors自动创建线程池
        //newFixedThreadPool(int nThreads)	创建固定大小的线程池
        //newSingleThreadExecutor()	创建只有一个线程的线程池
        //newCachedThreadPool()	创建一个不限线程数上限的线程池，任何提交的任务都将立即执行
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        //任务太多，线程池大小为10，其余任务会加入到队列当中，由于没有指定队列大小，可能会造成OOM异常
        for (int i = 0; i < 600; i++) {
            executorService.execute(() -> {
                if (executorService instanceof ThreadPoolExecutor) {
                    int queueSize = ((ThreadPoolExecutor) executorService).getQueue().size();
                    System.out.println("队列大小：" + queueSize);
                }
                System.out.println("当前线程:" + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

    }
}
