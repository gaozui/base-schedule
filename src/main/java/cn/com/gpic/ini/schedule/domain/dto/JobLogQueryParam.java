package cn.com.gpic.ini.schedule.domain.dto;

import cn.com.gpic.ini.common.util.wrapper.condition.annotations.SqlCondition;
import cn.com.gpic.ini.common.util.wrapper.condition.annotations.SqlOrderBy;
import cn.com.gpic.ini.common.util.wrapper.condition.domain.SqlSymbol;
import lombok.Data;

import java.util.Date;

@Data
public class JobLogQueryParam {

    @SqlCondition(SqlSymbol.LIKE)
    private String jobName;

    private String jobGroup;

    @SqlCondition(SqlSymbol.LIKE)
    private String invokeTarget;

    private String status;

    @SqlCondition(value = SqlSymbol.GE, columns = "CREATED_TIME")
    private Date startTime;

    @SqlCondition(value = SqlSymbol.LE, columns = "CREATED_TIME")
    private Date stopTime;

    @SqlOrderBy
    private Date createdTime;

}
