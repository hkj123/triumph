package com.accumulate.business.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Time;

/**
 * 用户表
 */
@SuppressWarnings("serial")   //抑制编译告警
@Data
@TableName(value = "Job_Operate_Log")
public class JobOperateLog extends SuperEntity<JobOperateLog> {

    private String jobClassName;

    private String jobGroupName;

    private String cronExpression;

    private String jobType;

    private Time operateTime;
}
