package cn.com.gpic.ini.schedule.service;

import cn.com.gpic.ini.common.domain.PageQuery;
import cn.com.gpic.ini.schedule.domain.dto.JobLogQueryParam;
import cn.com.gpic.ini.schedule.entity.IniJobLog;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 定时任务调度日志表 服务类
 * </p>
 *
 * @author lzk&yjj
 * @since 2022-10-18
 */
public interface IniJobLogService extends IService<IniJobLog> {

    /**
     * 根据条件查询定时任务调度日志分页列表
     */
    IPage<IniJobLog> listByParams(JobLogQueryParam param, PageQuery pageQuery);

    /**
     * 根据id查询定时任务调度日志
     */
    IniJobLog getInfoById(String id);

    /**
     * 删除定时任务调度日志
     */
    void removeByIds(String[] jobLogIds);

    /**
     * 清空定时任务调度日志
     */
    void clean();

}
