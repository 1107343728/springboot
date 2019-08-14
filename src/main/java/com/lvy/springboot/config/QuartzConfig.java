package com.lvy.springboot.config;

import com.lvy.springboot.job.TestJob;
import org.apache.commons.lang3.time.DateUtils;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * Description : 定时器配置，可以配置多个 Author： liweixiong Create Date： 2019/6/12 17:25
 */
@Configuration
public class QuartzConfig {
	String cron1 = "30 * * * * ? ";
	String cron2 = "0 0/1 * * * ? ";
	//船期
	@Bean
	public JobDetail testJob() {
		return JobBuilder.newJob(TestJob.class).withIdentity("testJob").storeDurably().build();
	}
	@Bean
	public Trigger sailQuartzTrigger() {
		// 方式一：用指定调度方法
		/*SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
		       .withIntervalInSeconds(30) //30
		       .repeatForever();
		return TriggerBuilder.newTrigger().forJob(testScheduleJob())
		       .withIdentity("testScheduleJob")
		       .withSchedule(scheduleBuilder)
		       .build();*/
		// 方式二：cron表达式
		Date cur = new Date();
		cur = DateUtils.addMinutes(cur,1); //延迟1分钟
		return TriggerBuilder.newTrigger().forJob(testJob()).withIdentity("testJob")
				.withSchedule(CronScheduleBuilder.cronSchedule(cron1)).startAt(cur) // 延迟1分钟
				.build();
	}


}
