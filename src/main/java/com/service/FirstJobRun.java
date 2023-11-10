package com.service;

import com.commonUtils.CommonUtils;
import com.jobs.FirstJob;
import com.model.TriggerInfo;
import com.scheduler.MainSchedular;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FirstJobRun {
    private final MainSchedular schedular;
    private final CommonUtils commonUtils;

    @PostConstruct
    public void init(){
        TriggerInfo info = commonUtils.getTriggerInfoObj(200,
                false,1000L,1000L,"info");
        schedular.scheduleJob(FirstJob.class,info);
    }


}
