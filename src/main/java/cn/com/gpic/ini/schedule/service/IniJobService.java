package cn.com.gpic.ini.schedule.service;

import cn.com.gpic.ini.common.domain.PageQuery;
import cn.com.gpic.ini.schedule.exception.TaskException;
import cn.com.gpic.ini.schedule.domain.dto.JobQueryParam;
import cn.com.gpic.ini.schedule.entity.IniJob;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.quartz.SchedulerException;

/**
 * <p>
 * 定时任务调度表 服务类
 * </p>
 *
 * @author lzk&yjj
 * @since 2022-10-18
 */
public interface IniJobService extends IService<IniJob> {

    /**
     * 根据条件查询定时任务分页列表
     */
    IPage<IniJob> listByParams(PageQuery pageQuery, JobQueryParam param);

    /**
     * 根据id查询定时任务
     */
    IniJob getInfoById(String id);

    /**
     * 保存定时任务数据
     */
    boolean insertJob(IniJob entity) throws SchedulerException, TaskException;

    /**
     * 更新定时任务信息
     */
    boolean updateJob(IniJob entity) throws SchedulerException, TaskException;

    /**
     * 定时任务状态修改
     */
    boolean changeStatus(IniJob entity) throws SchedulerException;

    /**
     * 定时任务立即执行一次
     */
    void run(IniJob entity) throws SchedulerException;

    /**
     * 删除定时任务
     */
    void deleteJobByIds(String[] jobIds) throws SchedulerException;

}
