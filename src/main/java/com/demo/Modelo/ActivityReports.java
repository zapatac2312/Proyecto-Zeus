package com.demo.Modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;



public class ActivityReports {
    private Long traineeId;
    private Long trainerId;
    private String traineeName;
    private String TrainerName;
    private LocalDate trainingDate;
    private String trainingType;
    private Integer duration;
    private String traineeEmail;

    public ActivityReports() {
    }

    public ActivityReports(Long traineeId, Long trainerId, String traineeName, String trainerName, LocalDate trainingDate, String trainingType, Integer duration, String traineeEmail) {
        this.traineeId = traineeId;
        this.trainerId = trainerId;
        this.traineeName = traineeName;
        TrainerName = trainerName;
        this.trainingDate = trainingDate;
        this.trainingType = trainingType;
        this.duration = duration;
        this.traineeEmail = traineeEmail;
    }

    public String getTraineeEmail() {
        return traineeEmail;
    }

    public void setTraineeEmail(String traineeEmail) {
        this.traineeEmail = traineeEmail;
    }

    public Long getTraineeId() {
        return traineeId;
    }

    public void setTraineeId(Long traineeId) {
        this.traineeId = traineeId;
    }

    public Long getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Long trainerId) {
        this.trainerId = trainerId;
    }

    public String getTraineeName() {
        return traineeName;
    }

    public void setTraineeName(String traineeName) {
        this.traineeName = traineeName;
    }

    public String getTrainerName() {
        return TrainerName;
    }

    public void setTrainerName(String trainerName) {
        TrainerName = trainerName;
    }

    public LocalDate getTrainingDate() {
        return trainingDate;
    }

    public void setTrainingDate(LocalDate trainingDate) {
        this.trainingDate = trainingDate;
    }

    public String getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(String trainingType) {
        this.trainingType = trainingType;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}