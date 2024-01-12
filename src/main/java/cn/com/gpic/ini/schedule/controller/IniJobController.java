package cn.com.gpic.ini.schedule.controller;

import cn.com.gpic.ini.common.config.jwt.JWTPassToken;
import cn.com.gpic.ini.common.domain.PageQuery;
import cn.com.gpic.ini.schedule.domain.dto.JobQueryParam;
import cn.com.gpic.ini.schedule.entity.IniJob;
import cn.com.gpic.ini.schedule.service.IniJobService;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.vampireachao.stream.plugin.mybatisplus.Database;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.quartz.CronExpression;
import org.quartz.SchedulerException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 定时任务调度表 前端控制器
 * </p>
 *
 * @author Gpic
 * @since 2022-10-18
 */
@Api(tags = "定时任务调度管理接口")
@RestController
@RequestMapping("/${base.web}/job")
public class IniJobController {

    @Resource
    private IniJobService service;

    @ApiOperation("根据条件查询定时任务分页列表")
    @GetMapping("/list")
    @JWTPassToken
    public IPage<IniJob> listByParams(JobQueryParam param, PageQuery pageQuery) {
        return service.listByParams(pageQuery, param);
    }

    @ApiOperation("根据id查询定时任务")
    @GetMapping("/{id}")
    @JWTPassToken
    public IniJob get(@PathVariable("id") String id) {
        return Database.getById(id, IniJob.class);
    }

    @ApiOperation("保存定时任务数据")
    @PostMapping
    @JWTPassToken
    public boolean add(@RequestBody IniJob entity) throws Exception {
        return service.insertJob(entity);
    }

    @ApiOperation("更新定时任务信息")
    @PutMapping
    @JWTPassToken
    public boolean updateJob(@RequestBody IniJob entity) throws Exception {
        Assert.isTrue(CronExpression.isValidExpression(entity.getCronExpression()), "cron表达式不正确");
        return service.updateJob(entity);
    }

    @ApiOperation("定时任务状态修改")
    @PutMapping("/changeStatus")
    @JWTPassToken
    public boolean changeStatus(@RequestBody IniJob entity) throws SchedulerException {
        return service.changeStatus(entity);
    }

    @ApiOperation("定时任务立即执行一次")
    @PutMapping("/run")
    @JWTPassToken
    public void run(@RequestBody IniJob entity) throws SchedulerException {
        service.run(entity);
    }

    @ApiOperation("删除定时任务")
    @DeleteMapping("/{jobIds}")
    @JWTPassToken
    public void remove(@PathVariable String[] jobIds) throws SchedulerException {
        service.deleteJobByIds(jobIds);
    }

}
