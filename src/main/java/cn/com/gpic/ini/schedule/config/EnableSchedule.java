package cn.com.gpic.ini.schedule.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author lzk&yjj
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({ScheduleAutoConfiguration.class})
@Documented
@Inherited
public @interface EnableSchedule {
}
