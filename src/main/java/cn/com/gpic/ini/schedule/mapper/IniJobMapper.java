package cn.com.gpic.ini.schedule.mapper;

import cn.com.gpic.ini.schedule.entity.IniJob;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 定时任务调度表 Mapper 接口
 * </p>
 *
 * @author lzk&yjj
 * @since 2022-10-18
 */
@Mapper
public interface IniJobMapper extends BaseMapper<IniJob> {

}
