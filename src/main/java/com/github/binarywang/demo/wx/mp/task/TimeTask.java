package com.github.binarywang.demo.wx.mp.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Auther: szp
 * @Date: 2019/11/16 21:34
 * @Description: 沈泽鹏写点注释吧
 */
@Component
public class TimeTask {
    @Scheduled(cron = "*/5 * * * * ?")
    public void getConsole(){
        System.out.println("springScheduled");

    }
}
