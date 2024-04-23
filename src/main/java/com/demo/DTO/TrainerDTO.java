package com.demo.DTO;


import com.demo.Modelo.Trainee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TrainerDTO {

    private String name;
    private String email;
    private String speciality;
    private String experience;
    private String certifications;
    private List<Trainee> trainee;

}