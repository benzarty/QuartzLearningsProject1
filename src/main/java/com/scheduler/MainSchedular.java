package com.scheduler;

import com.commonUtils.CommonUtils;
import com.model.TriggerInfo;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.AllArgsConstructor;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class MainSchedular {
    private final Scheduler scheduler;
    private final CommonUtils commonUtils;

    @PostConstruct
    public void startSchedule(){
        try {
            scheduler.start();
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }
    public void scheduleJob(Class className, TriggerInfo info){

        try {
            JobDetail jobDetail = commonUtils.getJobDetail(className,info);
            Trigger triggerDetail = commonUtils.getTriggerInfoOfJob(className,info);
            scheduler.scheduleJob(jobDetail,triggerDetail);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }
    public void scheduleJobWithPriority(Class className, TriggerInfo info){

        try {
            JobDetail jobDetail = commonUtils.getJobDetail(className,info);
            Trigger triggerDetail = commonUtils.getTriggerInfoOfJobWithPriority(className,info);
            scheduler.scheduleJob(jobDetail,triggerDetail);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    public void scheduleJob(Class className, String cronExp){

        try {
            JobDetail jobDetail = commonUtils.getJobDetail(className);
            Trigger triggerDetail = commonUtils.getTriggerByCronExpression(className,cronExp);
            scheduler.scheduleJob(jobDetail,triggerDetail);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    @PreDestroy
    public void closeScheuler(){
        try {
            scheduler.shutdown();
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    public  List<JobDetail> getAllJobDetails(){
        List<JobDetail> jobDetailList = new ArrayList<>();
        try {
            Set<JobKey> jobKeys = scheduler.getJobKeys(GroupMatcher.anyGroup());
            for(JobKey jobKey:jobKeys){
               jobDetailList.add(scheduler.getJobDetail(jobKey));
            }
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
        return jobDetailList;
    }

}
