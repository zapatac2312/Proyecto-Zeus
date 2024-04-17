package com.demo.Service;

import com.demo.DTO.TraineeDTO;
import com.demo.DTO.TraineeMapper;
import com.demo.Modelo.ActivityReports;
import com.demo.Modelo.Trainee;
import com.demo.Modelo.Trainer;
import com.demo.Modelo.TrainingCategories;
import com.demo.Repository.TraineeRepository;
import com.demo.Repository.TrainerRepository;
import org.apache.catalina.User;
import org.apache.catalina.UserDatabase;
import org.mapstruct.control.MappingControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.ID;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class TraineeService {

    private TraineeRepository traineeRepository;

    @Autowired
    public TraineeService(TraineeRepository traineeRepository) {
        this.traineeRepository = traineeRepository;
    }


    public TraineeDTO addTrainee(Trainee trainee){

        TraineeDTO traineeDTO = TraineeMapper.mapper.traineeToTraineeDTO(trainee);
        this.traineeRepository.save(trainee);
        return traineeDTO;
    }



    public TraineeDTO updateTraineeInfo(String email, String password,Trainee updateTraineeInfo ) {
        Trainee existingTrainee = traineeRepository.findByEmailAndPassword(email, password);
        if (existingTrainee != null) {

            existingTrainee.setName(updateTraineeInfo.getName());
            existingTrainee.setEmail(updateTraineeInfo.getEmail());
            existingTrainee.setDateOfBirth(updateTraineeInfo.getDateOfBirth());
            existingTrainee.setGender(updateTraineeInfo.getGender());
            existingTrainee.setTrainingGoal(updateTraineeInfo.getTrainingGoal());
            existingTrainee.setFitnessLevel(updateTraineeInfo.getFitnessLevel());


            this.traineeRepository.save(existingTrainee);
            return TraineeMapper.mapper.traineeToTraineeDTO(updateTraineeInfo);

        }
        else {
            throw new RuntimeException("Trainee not found");
        }
    }
    public TraineeDTO showTraineeInfo(String email, String password){
        Trainee existingTrainee = traineeRepository.findByEmailAndPassword(email, password);
        if (existingTrainee != null) {
            return TraineeMapper.mapper.traineeToTraineeDTO(existingTrainee);
        } else {
            throw new RuntimeException("Trainee not found");
        }
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
