package com.scheduler;

import com.commonUtils.CommonUtils;
import com.model.TriggerInfo;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.AllArgsConstructor;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.stereotype.Service;

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


}
