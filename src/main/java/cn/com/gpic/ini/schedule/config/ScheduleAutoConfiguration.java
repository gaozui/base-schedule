package cn.com.gpic.ini.schedule.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author lzk&yjj
 */
@ComponentScan({"cn.com.gpic.ini.schedule"})
@MapperScan("cn.com.gpic.ini.schedule.mapper")
public class ScheduleAutoConfiguration {
    public ScheduleAutoConfiguration() {
    }
}
