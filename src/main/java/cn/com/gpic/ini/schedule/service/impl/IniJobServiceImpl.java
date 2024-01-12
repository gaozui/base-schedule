package cn.com.gpic.ini.schedule.service.impl;

import cn.com.gpic.ini.common.domain.PageQuery;
import cn.com.gpic.ini.common.util.wrapper.WrapperUtils;
import cn.com.gpic.ini.schedule.exception.TaskException;
import cn.com.gpic.ini.schedule.constant.ScheduleConstants;
import cn.com.gpic.ini.schedule.domain.dto.JobQueryParam;
import cn.com.gpic.ini.schedule.entity.IniJob;
import cn.com.gpic.ini.schedule.mapper.IniJobMapper;
import cn.com.gpic.ini.schedule.service.IniJobService;
import cn.com.gpic.ini.schedule.util.ScheduleUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 定时任务调度表 服务实现类
 * </p>
 *
 * @author lzk&yjj
 * @since 2022-10-18
 */
@Service
@Slf4j
public class IniJobServiceImpl extends ServiceImpl<IniJobMapper, IniJob> implements IniJobService, CommandLineRunner {

    @Resource
    private Scheduler scheduler;

    @Override
    public IPage<IniJob> listByParams(PageQuery pageQuery, JobQueryParam param) {
        return this.page(pageQuery.build(), WrapperUtils.query(param, IniJob.class));
    }

    @Override
    public IniJob getInfoById(String id) {
        return this.getById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insertJob(IniJob entity) throws SchedulerException, TaskException {
        if (!CronExpression.isValidExpression(entity.getCronExpression())) {
            throw new RuntimeException("cron表达式不正确");
        }
        entity.setStatus(ScheduleConstants.Status.PAUSE.getValue());
        boolean flag = this.save(entity);
        if (flag) {
            ScheduleUtils.createScheduleJob(scheduler, entity);
        }
        return flag;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateJob(IniJob entity) throws SchedulerException, TaskException {
        IniJob properties = this.getById(entity.getId());
        boolean flag = this.updateById(entity);
        if (flag) {
            updateSchedulerJob(entity, properties.getJobGroup());
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean changeStatus(IniJob entity) throws SchedulerException {
        IniJob job = this.getById(entity.getId());
        boolean rows = false;
        if (ScheduleConstants.Status.NORMAL.getValue().equals(entity.getStatus())) {
            rows = resumeJob(job);
        } else if (ScheduleConstants.Status.PAUSE.getValue().equals(entity.getStatus())) {
            rows = pauseJob(job);
        }
        return rows;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void run(IniJob entity) throws SchedulerException {
        String jobId = entity.getId();
        String jobGroup = entity.getJobGroup();
        IniJob properties = this.getById(jobId);
        // 参数
        JobDataMap dataMap = new JobDataMap();
        dataMap.put(ScheduleConstants.TASK_PROPERTIES, properties);
        scheduler.triggerJob(ScheduleUtils.getJobKey(jobId, jobGroup), dataMap);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteJobByIds(String[] jobIds) throws SchedulerException {
        for (String jobId : jobIds) {
            IniJob job = this.getById(jobId);
            String jobGroup = job.getJobGroup();
            boolean flag = this.removeById(jobId);
            if (flag) {
                scheduler.deleteJob(ScheduleUtils.getJobKey(jobId, jobGroup));
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean resumeJob(IniJob job) throws SchedulerException {
        String jobId = job.getId();
        String jobGroup = job.getJobGroup();
        job.setStatus(ScheduleConstants.Status.NORMAL.getValue());
        boolean flag = this.updateById(job);
        if (flag) {
            scheduler.resumeJob(ScheduleUtils.getJobKey(jobId, jobGroup));
        }
        return flag;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean pauseJob(IniJob job) throws SchedulerException {
        String jobId = job.getId();
        String jobGroup = job.getJobGroup();
        job.setStatus(ScheduleConstants.Status.PAUSE.getValue());
        boolean flag = this.updateById(job);
        if (flag) {
            scheduler.pauseJob(ScheduleUtils.getJobKey(jobId, jobGroup));
        }
        return flag;

    }

    private void updateSchedulerJob(IniJob entity, String jobGroup) throws SchedulerException, TaskException {
        String jobId = entity.getId();
        // 判断是否存在
        JobKey jobKey = ScheduleUtils.getJobKey(jobId, jobGroup);
        if (scheduler.checkExists(jobKey)) {
            // 防止创建时存在数据问题 先移除，然后在执行创建操作
            scheduler.deleteJob(jobKey);
        }
        ScheduleUtils.createScheduleJob(scheduler, entity);
    }

    @Override
    public void run(String... args) throws Exception {
        scheduler.clear();
        List<IniJob> jobList = this.list();
        for (IniJob job : jobList) {
            ScheduleUtils.createScheduleJob(scheduler, job);
        }
    }
}
