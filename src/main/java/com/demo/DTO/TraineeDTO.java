package com.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TraineeDTO {

    private String name;
    private String email;
    private String gender;
    private String trainingGoal;
    private String fitnessLevel;

}