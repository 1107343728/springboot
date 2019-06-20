package com.lvy.springboot;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WorkSchedule {

    //@Scheduled(cron="0/5 * *  * * ? ") //每5秒
    @Scheduled(cron="0 0/5 *  * * ? ") //每5分钟
    //@Scheduled(cron="0 15 10 L * ?") //每月最后一日的上午10:15触发
    //@Scheduled(cron="0 15 10 ? * 6L") //每月最后一日的上午10:15触发
    public void collectData() {
        System.out.println("Schedule start............ ");
        System.out.println("do............");
        System.out.println("Schedule end............ ");
    }


}
