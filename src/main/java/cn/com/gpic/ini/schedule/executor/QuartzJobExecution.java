package cn.com.gpic.ini.schedule.executor;

import cn.com.gpic.ini.schedule.entity.IniJob;
import cn.com.gpic.ini.schedule.util.JobInvokeUtil;
import org.quartz.JobExecutionContext;

/**
 * 定时任务处理（允许并发执行）
 *
 * @author lzk&yjj
 */
public class QuartzJobExecution extends AbstractQuartzJob {
    @Override
    protected void doExecute(JobExecutionContext context, IniJob sysJob) throws Exception {
        JobInvokeUtil.invokeMethod(sysJob);
    }
}
