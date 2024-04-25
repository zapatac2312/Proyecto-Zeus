package com.demo.DTO;


import com.demo.Modelo.Trainee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
public class TrainerDTO {

    private String name;
    private String email;
    private String speciality;
    private String experience;
    private String certifications;
    private List<Trainee> trainee;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getCertifications() {
        return certifications;
    }

    public void setCertifications(String certifications) {
        this.certifications = certifications;
    }

    public List<Trainee> getTrainee() {
        return trainee;
    }

    public void setTrainee(List<Trainee> trainee) {
        this.trainee = trainee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainerDTO trainerDTO = (TrainerDTO) o;
        return Objects.equals(name, trainerDTO.name) &&
                Objects.equals(email, trainerDTO.email);
    }


}