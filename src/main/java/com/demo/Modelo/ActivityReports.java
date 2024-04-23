package com.demo.Modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ActivityReports {
    //preguntar
    private Long traineeId;
    private Long trainerId;
    private String traineeName;
    private String TrainerName;
    private LocalDate trainingDate;
    private String trainingType;
    private Integer duration;

}