package com.mdd.admin.config.quartz.exceution;

import com.mdd.admin.config.quartz.InvokeUtils;
import com.mdd.common.entity.Crontab;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;

/**
 * 禁止并发任务
 */
@DisallowConcurrentExecution
public class QuartzDisExecution extends AbstractQuartzJob {

    @Override
    protected void doExecute(JobExecutionContext context, Crontab crontab) throws Exception {
        InvokeUtils.invokeMethod(crontab);
    }

}