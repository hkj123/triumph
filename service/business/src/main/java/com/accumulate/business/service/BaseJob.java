package com.accumulate.business.service;


import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by hukj
 */
public interface BaseJob extends Job {

    public void execute(JobExecutionContext context) throws JobExecutionException;
}
