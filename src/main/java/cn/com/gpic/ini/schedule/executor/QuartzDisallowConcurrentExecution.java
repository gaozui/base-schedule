package cn.com.gpic.ini.schedule.executor;

import cn.com.gpic.ini.schedule.entity.IniJob;
import cn.com.gpic.ini.schedule.util.JobInvokeUtil;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;

/**
 * 定时任务处理（禁止并发执行）
 *
 * @author lzk&yjj
 */
@DisallowConcurrentExecution
public class QuartzDisallowConcurrentExecution extends AbstractQuartzJob {
    @Override
    protected void doExecute(JobExecutionContext context, IniJob sysJob) throws Exception {
        JobInvokeUtil.invokeMethod(sysJob);
    }
}
