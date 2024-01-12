package cn.com.gpic.ini.schedule.domain.dto;

import cn.com.gpic.ini.common.util.wrapper.condition.annotations.SqlCondition;
import cn.com.gpic.ini.common.util.wrapper.condition.domain.SqlSymbol;
import lombok.Data;

@Data
public class JobQueryParam {

    @SqlCondition(SqlSymbol.LIKE)
    private String jobName;

    private String jobGroup;

    @SqlCondition(SqlSymbol.LIKE)
    private String invokeTarget;

    private String status;
}
