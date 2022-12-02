package com.mdd.admin.config.quartz;

import org.quartz.CronExpression;

import java.text.ParseException;
import java.util.Date;

/**
 * 表达式工具
 */
public class CronUtils {

    /**
     * 验证表达式是否有效
     *
     * @param cronExpression 表达式
     * @return true=有效,false=无效
     */
    public static boolean isValid(String cronExpression) {
        return CronExpression.isValidExpression(cronExpression);
    }

    /**
     * 验证表达式消息无效给出有效性
     *
     * @param cronExpression 表达式
     * @return null=有效, 其它=无效时的错误描述
     */
    public static String invalidMessage(String cronExpression) {
        try {
            new CronExpression(cronExpression);
            return null;
        } catch (ParseException pe) {
            return pe.getMessage();
        }
    }

    /**
     * 下一个执行时间点
     *
     * @param cronExpression n表达式
     * @return Date下次表达式执行时间
     */
    public static Date nextExecution(String cronExpression)
    {
        try {
            CronExpression cron = new CronExpression(cronExpression);
            return cron.getNextValidTimeAfter(new Date(System.currentTimeMillis()));
        } catch (ParseException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

}
