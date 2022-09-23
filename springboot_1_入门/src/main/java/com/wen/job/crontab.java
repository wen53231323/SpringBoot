package com.wen.job;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

/**
 * 定时任务类
 */
// @Async注解：该类中的所有方法都是异步任务
@Async
@Configuration
// @EnableScheduling注解：开启定时任务
@EnableScheduling
public class crontab {
    /**
     * Scheduled注解：设置任务的执行时间，三种属性配置方式
     * fixedDelay：上一次执行完毕之后再执行（默认）
     * fixedRate：上一次开始执行之后再执行
     * initialDelay：定义该任务延迟执行时间
     * cron：通过表达式来配置任务执行时间（表达式自行搜索）
     */
    // 第一次延迟2秒后执行，之后按fixedDelay的规则每5秒执行一次
    @Scheduled(initialDelay = 2000, fixedDelay = 5000)
    public void run() throws InterruptedException {
        Thread.sleep(6000);
        System.out.println(Thread.currentThread().getName() + "=====>>>>>" + LocalDateTime.now());
    }
}
