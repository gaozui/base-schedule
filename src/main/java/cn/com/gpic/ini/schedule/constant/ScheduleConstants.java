package cn.com.gpic.ini.schedule.constant;

/**
 * 任务调度通用常量
 *
 * @author lzk&yjj
 */
public class ScheduleConstants {

    /**
     * 执行类名
     */
    public static final String TASK_CLASS_NAME = "TASK_CLASS_NAME";

    /**
     * 执行目标key
     */
    public static final String TASK_PROPERTIES = "TASK_PROPERTIES";

    /**
     * 默认
     */
    public static final String MISFIRE_DEFAULT = "0";

    /**
     * 立即触发执行
     */
    public static final String MISFIRE_IGNORE_MISFIRES = "1";

    /**
     * 触发一次执行
     */
    public static final String MISFIRE_FIRE_AND_PROCEED = "2";

    /**
     * 不触发立即执行
     */
    public static final String MISFIRE_DO_NOTHING = "3";

    /**
     * 定时任务调度表名
     */
    public static final String TABLE_JOB = "SYS_JOB";

    /**
     * 定时任务调度日志表名
     */
    public static final String TABLE_JOB_LOG = "SYS_JOB_LOG";

    public enum Status {
        /**
         * 正常
         */
        NORMAL("0"),
        /**
         * 暂停
         */
        PAUSE("1");

        private String value;

        private Status(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
