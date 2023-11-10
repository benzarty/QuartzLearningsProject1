package com.controller;

import com.service.ScheduleForever;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class QuartzController {

    private final ScheduleForever scheduleForever;

    @GetMapping("/schedule")
    public void scheduleJob(){
        scheduleForever.init();
    }
}
