package com.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class FirstJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext)  {
        System.out.println("First Job in Qaurtz Scheduling...."+new Date(System.currentTimeMillis()));
    }
}
