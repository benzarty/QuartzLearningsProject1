package com.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TriggerInfo implements Serializable {
    private int triggerCount;
    private boolean isRunForever;
    private Long timeInterval;  //bin les jobs
    private Long initialOffSet;  //ba3ed 9adeh mili bdé projet yetrana
    private String info;

}
