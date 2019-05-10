package com.accumulate.business.controller;

import com.accumulate.business.model.JobAndTrigger;
import com.accumulate.business.model.JobInfo;
import com.accumulate.business.model.MyPage;
import com.accumulate.business.service.BaseJob;
import com.accumulate.business.service.JobOperateLogService;
import com.accumulate.business.utils.DateUnit;
import com.baomidou.mybatisplus.extension.api.ApiController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

import static org.quartz.DateBuilder.futureDate;

/**
 * Created by hukj
 */
@RestController
@RequestMapping(value = "/job")
@Api("跑批任务管理")
public class JobController extends ApiController {
    @Autowired
    private JobOperateLogService jobOperateLogService;
    //加入Qulifier注解，通过名称注入bean
    @Autowired
    @Qualifier("Scheduler")
    private Scheduler scheduler;

    /**
     * 添加任务
     *
     * @param jobInfo
     * @throws Exception
     */
//    {
//    "cronExpression": "0/2 * * * * ?",
//        "jobClassName": "NewJob",
//        "jobGroupName": "ceshi",
//        "jobType": "nomal"}
    @PostMapping(value = "/addjob")
    @ApiOperation(value = "添加任务")
    public void addjob(@RequestBody JobInfo jobInfo) throws Exception {
        if (Objects.isNull(jobInfo.getJobClassName()) || Objects.isNull(jobInfo.getJobGroupName()) || Objects.isNull(jobInfo.getCronExpression())) {
//            return new Result(Result.ReturnValue.SUCCESS,"ok");
            return;
        }
        if (Objects.isNull(jobInfo.getTimeType())) {
            addCronJob(jobInfo);
            return;
        } else {
            addSimpleJob(jobInfo);
            return;
        }
    }

    //CronTrigger
    public void addCronJob(JobInfo jobInfo) throws Exception {
        // 启动调度器
        scheduler.start();
        //构建job信息
        JobDetail jobDetail = JobBuilder.newJob(getClass(jobInfo.getJobClassName()).getClass()).
                withIdentity(jobInfo.getJobClassName(), jobInfo.getJobGroupName())
                .build();
        //表达式调度构建器(即任务执行的时间)
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(jobInfo.getCronExpression());
        //按新的cronExpression表达式构建一个新的trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().
                withIdentity(jobInfo.getJobClassName(), jobInfo.getJobGroupName())
                .withSchedule(scheduleBuilder)
                .build();
        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            System.out.println("创建定时任务失败" + e);
            throw new Exception("创建定时任务失败");
        }
    }

    //Simple Trigger
    public void addSimpleJob(JobInfo jobInfo) throws Exception {
        // 启动调度器
        scheduler.start();
        //构建job信息
        JobDetail jobDetail = JobBuilder.newJob(getClass(jobInfo.getJobClassName()).getClass())
                .withIdentity(jobInfo.getJobClassName(), jobInfo.getJobGroupName())
                .build();
        DateBuilder.IntervalUnit verDate = DateUnit.verification(jobInfo.getTimeType());
        SimpleTrigger simpleTrigger = (SimpleTrigger) TriggerBuilder.newTrigger()
                .withIdentity(jobInfo.getJobClassName(), jobInfo.getJobGroupName())
                .startAt(futureDate(Integer.parseInt(jobInfo.getCronExpression()), verDate))
//                .startAt(futureDate(Integer.parseInt(jobInfo.getCronExpression()), DateBuilder.IntervalUnit.DAY))
                .forJob(jobInfo.getJobClassName(), jobInfo.getJobGroupName())
                .build();
        try {
            scheduler.scheduleJob(jobDetail, simpleTrigger);
        } catch (SchedulerException e) {
            System.out.println("创建定时任务失败" + e);
            throw new Exception("创建定时任务失败");
        }
    }

    /**
     * 根据类名称，通过反射得到该类，然后创建一个BaseJob的实例。
     * 由于NewJob和HelloJob都实现了BaseJob，
     * 所以这里不需要我们手动去判断。这里涉及到了一些java多态调用的机制
     *
     * @param classname
     * @return
     * @throws Exception
     */
    public static BaseJob getClass(String classname) throws Exception {
        Class<?> class1 = Class.forName(classname);
        return (BaseJob) class1.newInstance();
    }

    /**
     * 暂停任务
     *
     * @param jobClassName
     * @param jobGroupName
     * @throws Exception
     */
    @PostMapping(value = "/pausejob")
    @ApiOperation(value = "停止任务")
    public void pausejob(@ApiParam(value = "jobClassName", required = true) @RequestParam(value = "jobClassName", required = true) String jobClassName,
                         @ApiParam(value = "jobGroupName", required = true) @RequestParam(value = "jobGroupName", required = true) String jobGroupName) throws Exception {
        try {
            scheduler.pauseJob(JobKey.jobKey(jobClassName, jobGroupName));
        } catch (SchedulerException e) {
            throw new Exception("更新定时任务失败");
        }
    }

    /**
     * 恢复任务
     *
     * @param jobClassName
     * @param jobGroupName
     * @throws Exception
     */
    @PostMapping(value = "/resumejob")
    @ApiOperation(value = "恢复任务")
    public void resumejob(@ApiParam(value = "jobClassName", required = true) @RequestParam(value = "jobClassName") String jobClassName,
                          @ApiParam(value = "jobGroupName", required = true) @RequestParam(value = "jobGroupName") String jobGroupName) throws Exception {
        try {
            scheduler.resumeJob(JobKey.jobKey(jobClassName, jobGroupName));
        } catch (SchedulerException e) {
            throw new Exception("更新定时任务失败");
        }
    }

    /**
     * 更新任务
     *
     * @param jobClassName
     * @param jobGroupName
     * @param cronExpression
     * @throws Exception
     */
    @PostMapping(value = "/reschedulejob")
    @ApiOperation(value = "更新任务")
    public void rescheduleJob(@ApiParam(value = "jobClassName", required = true) @RequestParam(value = "jobClassName") String jobClassName,
                              @ApiParam(value = "jobGroupName", required = true) @RequestParam(value = "jobGroupName") String jobGroupName,
                              @ApiParam(value = "cronExpression", required = true) @RequestParam(value = "cronExpression") String cronExpression) throws Exception {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName, jobGroupName);
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            throw new Exception("更新定时任务失败");
        }
    }

    /**
     * 删除任务
     * 删除操作前应该暂停该任务的触发器，并且停止该任务的执行
     *
     * @param jobClassName
     * @param jobGroupName
     * @throws Exception
     */
    @PostMapping(value = "/deletejob")
    @ApiOperation(value = "删除任务")
    public void deletejob(@ApiParam(value = "jobClassName", required = true) @RequestParam(value = "jobClassName") String jobClassName,
                          @ApiParam(value = "jobGroupName", required = true) @RequestParam(value = "jobGroupName") String jobGroupName) throws Exception {
        try {
            scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName, jobGroupName));
            scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName, jobGroupName));
            scheduler.deleteJob(JobKey.jobKey(jobClassName, jobGroupName));
        } catch (SchedulerException e) {
            logger.info(e.toString());
            throw new Exception("更新定时任务失败");
        }
    }

    /**
     * 查询任务
     *
     * @return
     */
    @PostMapping(value = "/queryjob")
    @ApiOperation(value = "查询任务")
    public MyPage<JobAndTrigger> queryjob(@RequestBody MyPage myPage) {
        MyPage<JobAndTrigger> jobAndTrigger = jobOperateLogService.getJobAndTriggerDetails(myPage);
        return jobAndTrigger;
    }
}
