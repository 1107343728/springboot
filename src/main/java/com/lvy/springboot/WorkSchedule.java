package com.lvy.springboot;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WorkSchedule {

    @Scheduled(cron="0/5 * *  * * ? ") //每5秒
    //@Scheduled(cron="0 0/5 *  * * ? ") //每5分钟
    public void collectData() {
        System.out.println("Schedule start............ ");
        System.out.println("do............");
        System.out.println("Schedule end............ ");
    }


}
