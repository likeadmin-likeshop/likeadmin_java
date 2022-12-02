package com.mdd.admin.crontab;


import org.springframework.stereotype.Component;


/**
 * 工作类的具体实现,即需要定时执行的“某个事件”
 */
@Component("myJob")
public class MyJob  {

    public void handle(String s) {
        System.out.println("执行无参方法: " + s);
    }

}
