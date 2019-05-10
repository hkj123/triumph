package com.accumulate.business.model;

import lombok.Data;

import java.math.BigInteger;

/**
 * Created by hukj
 */
@Data
public class JobAndTrigger {
    private String jobName;
    private String jobGroup;
    private String jobClassName;
    private String triggerName;
    private String triggerGroup;
    private String cornExpression;
    private String timeZoneId;
}
