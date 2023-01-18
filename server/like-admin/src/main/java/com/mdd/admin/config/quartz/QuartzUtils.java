package com.mdd.admin.config.quartz;

import com.mdd.admin.config.quartz.exceution.QuartzDisExecution;
import com.mdd.admin.config.quartz.exceution.QuartzJobExecution;
import com.mdd.common.entity.Crontab;
import com.mdd.common.util.StringUtils;
import org.quartz.*;

/**
 * 计划任务工具
 */
public class QuartzUtils {

    /**
     * 得到quartz任务类
     *
     * @param crontab 执行计划
     * @return 具体执行任务类
     */
    private static Class<? extends Job> getQuartzJobClass(Crontab crontab) {
        boolean isConcurrent = crontab.getConcurrent().equals(0);
        return isConcurrent ? QuartzJobExecution.class : QuartzDisExecution.class;
    }

    /**
     * 构建任务对象Key
     *
     * @param jobId (任务ID)
     * @param jobGroup (任务分组)
     * @return JobKey
     */
    public static JobKey getJobKey(Integer jobId, String jobGroup) {
        return JobKey.jobKey(TaskConstants.TASK_CLASS_NAME + jobId, jobGroup);
    }

    /**
     * 构建触发对象Key
     *
     * @param jobId (任务ID)
     * @param jobGroup (任务分组)
     * @return TriggerKey
     */
    public static TriggerKey getTriggerKey(Integer jobId, String jobGroup) {
        return TriggerKey.triggerKey(TaskConstants.TASK_PROPERTIES + jobId, jobGroup);
    }

    /**
     * 创建定时任务
     *
     * @param scheduler 调度器
     * @param job 任务
     * @throws SchedulerException 调度异常
     */
    public static void createScheduleJob(Scheduler scheduler, Crontab job) throws SchedulerException {
        Integer jobId     = job.getId();
        String jobGroup   = job.getTypes();
        String expression = job.getRules();

        // 构建任务
        Class<? extends Job> jobClass = getQuartzJobClass(job);
        JobDetail jobDetail = JobBuilder.newJob(jobClass)
                .withIdentity(getJobKey(jobId, jobGroup))
                .build();

        // 构建时间
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(expression);
        switch (job.getStrategy()) {
            case 1: // 立即执行
                cronScheduleBuilder = cronScheduleBuilder.withMisfireHandlingInstructionIgnoreMisfires();
                break;
            case 2: // 执行一次
                cronScheduleBuilder = cronScheduleBuilder.withMisfireHandlingInstructionFireAndProceed();
                break;
            case 3: // 放弃执行
                cronScheduleBuilder = cronScheduleBuilder.withMisfireHandlingInstructionDoNothing();
                break;
        }

        // 注入参数
        jobDetail.getJobDataMap().put(TaskConstants.TASK_PROPERTIES, job);

        // 构建目标
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(getTriggerKey(jobId, jobGroup))
                .withSchedule(cronScheduleBuilder)
                .build();

        // 如果存在则删除
        if (scheduler.checkExists(getJobKey(jobId, jobGroup))) {
            scheduler.deleteJob(getJobKey(jobId, jobGroup));
        }

        // 如果过期则调度
        if (StringUtils.isNotNull(CronUtils.nextExecution(job.getRules()))) {
            scheduler.scheduleJob(jobDetail, trigger);
        }

        // 如果暂停则停止
        if (!job.getStatus().equals(TaskConstants.STATUS_RUN)) {
            scheduler.pauseJob(getJobKey(jobId, jobGroup));
        }
    }

}
