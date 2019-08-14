package com.lvy.springboot.job;

import cn.hutool.core.date.DateUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * Description :
 * Author：          liweixiong
 * Create Date：     2019/8/14 11:43
 */
public class TestJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("当前时间是：" + DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
    }

    public static void main(String[] args) {
        System.out.println(DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
    }
}
