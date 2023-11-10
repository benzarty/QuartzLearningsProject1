package com.controller;

import com.scheduler.MainSchedular;
import com.service.ScheduleForever;
import lombok.AllArgsConstructor;
import org.quartz.JobDetail;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class QuartzController {
    private final MainSchedular mainSchedular;

    private final ScheduleForever scheduleForever;

    @GetMapping("/schedule")
    public void scheduleJob(){
        scheduleForever.init();
    }

    @GetMapping("/getAllJobs")
    public  List<JobDetail>   getAllJobs(){
      return   mainSchedular.getAllJobDetails();
    }

    @GetMapping("/getSpecificJob/{groupName}/{jobName}")
    public JobDetail getJobDetail(@PathVariable("groupName") String groupName,
                             @PathVariable("jobName") String jobName){
        return  mainSchedular.getSpecificJobDetail(jobName, groupName);

    }
}
