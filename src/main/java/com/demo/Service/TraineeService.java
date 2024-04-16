package com.demo.Service;

import com.demo.DTO.TraaineeMapper;
import com.demo.DTO.TraineeDTO;
import com.demo.Modelo.ActivityReports;
import com.demo.Modelo.Trainee;
import com.demo.Modelo.Trainer;
import com.demo.Modelo.TrainingCategories;
import com.demo.Repository.TraineeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TraineeService {

    private TraineeRepository traineeRepository;

    @Autowired
    public TraineeService(TraineeRepository traineeRepository) {
        this.traineeRepository = traineeRepository;
    }


    public TraineeDTO addTrainee(Trainee trainee){

        TraineeDTO traineeDTO = TraaineeMapper.mapper.traineeToTraineeDTO(trainee);
        this.traineeRepository.save(trainee);
        return traineeDTO;
    }

    public Boolean updateTraineeInfo(String email, String password){
        return true;
    }

    public TraineeDTO showTraineeInfo(String email, String password){
        return null;
    }

    public Boolean changeTraineePassword(String email, String oldPassword){
        return true;
    }

    public ActivityReports addTraining(String email, TrainingCategories trainingCategories, Date date, Trainer trainer){
        return null;
    }

   public ActivityReports getMonthlyReport(String email, String password){
        return null;
   }















}
