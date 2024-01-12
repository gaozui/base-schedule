package cn.com.gpic.ini.schedule.controller;

import cn.com.gpic.ini.common.config.jwt.JWTPassToken;
import cn.com.gpic.ini.common.domain.PageQuery;
import cn.com.gpic.ini.schedule.domain.dto.JobLogQueryParam;
import cn.com.gpic.ini.schedule.entity.IniJobLog;
import cn.com.gpic.ini.schedule.service.IniJobLogService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 定时任务调度日志表 前端控制器
 * </p>
 *
 * @author Gpic
 * @since 2022-10-18
 */
@Api(tags = "定时任务调度日志管理接口")
@RestController
@RequestMapping("/${base.web}/jobLog")
public class IniJobLogController {

    @Resource
    private IniJobLogService service;

    @ApiOperation("根据条件查询定时任务调度日志分页列表")
    @GetMapping("/list")
    @JWTPassToken
    public IPage<IniJobLog> listByParams(JobLogQueryParam param, PageQuery pageQuery) {
        return service.listByParams(param, pageQuery);
    }

    @ApiOperation("根据id查询定时任务调度日志")
    @GetMapping("/{id}")
    @JWTPassToken
    public IniJobLog get(@PathVariable("id") String id) {
        return service.getInfoById(id);
    }

    @ApiOperation("删除定时任务调度日志")
    @DeleteMapping("/{jobLogIds}")
    @JWTPassToken
    public void remove(@PathVariable String[] jobLogIds) {
        service.removeByIds(jobLogIds);
    }

    @ApiOperation("清空定时任务调度日志")
    @DeleteMapping("/clean")
    @JWTPassToken
    public void clean() {
        service.clean();
    }
}
