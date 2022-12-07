package com.mdd.admin.crontab;


import org.springframework.stereotype.Component;


/**
 * 具体的定时任务
 */
@Component("myJob")
public class MyJob  {

    public void handle(String s) {
        // System.out.println("有参数定时任务执行逻辑 : " + s);
    }

}
