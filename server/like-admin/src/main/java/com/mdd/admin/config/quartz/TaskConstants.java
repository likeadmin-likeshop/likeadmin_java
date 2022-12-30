package com.mdd.admin.config.quartz;

/**
 * 计划任务常量
 */
public class TaskConstants {

    /** 执行任务名 */
    public static final String TASK_CLASS_NAME = "TASK_CLASS_NAME";

    /** 执行目标键 */
    public static final String TASK_PROPERTIES = "TASK_PROPERTIES";

    /** 状态: 运行 */
    public static final Integer STATUS_RUN   = 1;

    /** 状态: 失败 */
    public static final Integer STATUS_FAIL  = 2;

}
