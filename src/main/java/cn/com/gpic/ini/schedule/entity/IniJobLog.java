package cn.com.gpic.ini.schedule.entity;

import cn.com.gpic.ini.schedule.constant.ScheduleConstants;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 定时任务调度日志表
 * </p>
 *
 * @author lzk&yjj
 * @since 2022-10-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(ScheduleConstants.TABLE_JOB_LOG)
public class IniJobLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.ASSIGN_UUID)
    private String id;

    @TableField("JOB_NAME")
    private String jobName;

    @TableField("JOB_GROUP")
    private String jobGroup;

    @TableField("INVOKE_TARGET")
    private String invokeTarget;

    @TableField("JOB_MESSAGE")
    private String jobMessage;

    @TableField("STATUS")
    private String status;

    @TableField("EXCEPTION_INFO")
    private String exceptionInfo;

    @TableField(value = "CREATED_BY", fill = FieldFill.INSERT)
    private String createdBy;

    @TableField(value = "CREATED_TIME", fill = FieldFill.INSERT)
    private Date createdTime;

    @TableField(value = "UPDATED_BY", fill = FieldFill.INSERT_UPDATE)
    private String updatedBy;

    @TableField(value = "UPDATED_TIME", fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;


}
