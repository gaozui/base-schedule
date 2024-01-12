package cn.com.gpic.ini.schedule.service.impl;

import cn.com.gpic.ini.common.domain.PageQuery;
import cn.com.gpic.ini.common.util.wrapper.WrapperUtils;
import cn.com.gpic.ini.schedule.domain.dto.JobLogQueryParam;
import cn.com.gpic.ini.schedule.entity.IniJobLog;
import cn.com.gpic.ini.schedule.mapper.IniJobLogMapper;
import cn.com.gpic.ini.schedule.service.IniJobLogService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.vampireachao.stream.plugin.mybatisplus.Database;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * <p>
 * 定时任务调度日志表 服务实现类
 * </p>
 *
 * @author lzk&yjj
 * @since 2022-10-18
 */
@Service
public class IniJobLogServiceImpl extends ServiceImpl<IniJobLogMapper, IniJobLog> implements IniJobLogService {

    @Override
    public IPage<IniJobLog> listByParams(JobLogQueryParam param, PageQuery pageQuery) {
        return Database.page(pageQuery.build(), WrapperUtils.query(param, IniJobLog.class));
    }

    @Override
    public IniJobLog getInfoById(String id) {
        return Database.getById(id, IniJobLog.class);
    }

    @Override
    public void removeByIds(String[] jobLogIds) {
        Database.removeByIds(Arrays.asList(jobLogIds), IniJobLog.class);
    }

    @Override
    public void clean() {
        Database.remove(Wrappers.lambdaQuery(IniJobLog.class));
    }
}
