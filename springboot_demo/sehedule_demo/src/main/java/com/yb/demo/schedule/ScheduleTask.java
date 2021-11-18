package com.yb.demo.schedule;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author yb
 * @description 定时任务
 * @data 2021/7/1
 */
@Component
public class ScheduleTask {

    @Scheduled(cron = "0/1 * * * * ?")
    public void task(){
        System.out.println("=========scheduleTask============");
    }
}
