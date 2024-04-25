package com.demo.Service;

import com.demo.DTO.*;
import com.demo.Modelo.ActivityReports;
import com.demo.Modelo.Trainee;
import com.demo.Modelo.Trainer;
import com.demo.Repository.TraineeRepository;
import com.demo.Repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TraineeService {

    private TraineeRepository traineeRepository;
    private TrainerRepository trainerRepository;
    private WebClient webClient;

    @Autowired
    public TraineeService(TraineeRepository traineeRepository, TrainerRepository trainerRepository, WebClient webClient) {
        this.traineeRepository = traineeRepository;
        this.trainerRepository = trainerRepository;
        this.webClient= webClient;
    }

    public TraineeDTO addTrainee(Trainee trainee){
        if(trainee.getName()==null){
            throw new RuntimeException("A name is required");
        } else if(trainee.getEmail()==null){
            throw new RuntimeException("Invalid email");
        } else if(trainee.getPassword()==null){
            throw new RuntimeException("Invalid password");
        }
        TraineeDTO traineeDTO = TraineeMapper.mapper.traineeToTraineeDTO(trainee);
        if(traineeRepository.existsByEmail(trainee.getEmail())){
            throw new RuntimeException("Trainees email: "+ trainee.getEmail()+" already registered");
        }
        this.traineeRepository.save(trainee);
        return traineeDTO;
    }

    public TraineeDTO updateTraineeInfo(String email,Trainee updatedTraineeInfo ) {
        if(traineeRepository.existsByEmail(email)){
            Trainee existingTrainee = traineeRepository.findByEmail(email);
            if(updatedTraineeInfo.getName()==null){
                throw new RuntimeException("A name is required");
            } else if(updatedTraineeInfo.getEmail()==null){
                throw new RuntimeException("Invalid email");
            } else if(updatedTraineeInfo.getPassword()==null){
                throw new RuntimeException("Invalid password");
            }
            existingTrainee.setName(updatedTraineeInfo.getName());
            existingTrainee.setEmail(updatedTraineeInfo.getEmail());
            existingTrainee.setDateOfBirth(updatedTraineeInfo.getDateOfBirth());
            existingTrainee.setGender(updatedTraineeInfo.getGender());
            existingTrainee.setTrainingGoal(updatedTraineeInfo.getTrainingGoal());
            existingTrainee.setFitnessLevel(updatedTraineeInfo.getFitnessLevel());
            this.traineeRepository.save(existingTrainee);
            return TraineeMapper.mapper.traineeToTraineeDTO(updatedTraineeInfo);
        }else {
            throw new RuntimeException("The trainees information you want to update doesn't exist in our system");
        }
    }

    public TraineeDTO showTraineeInfo(String email){
        if(traineeRepository.existsByEmail(email)){
            Trainee existingTrainee = traineeRepository.findByEmail(email);
            return TraineeMapper.mapper.traineeToTraineeDTO(existingTrainee);
        }else {
            throw new RuntimeException("Trainee's email not found");
        }
    }

    public TrainerDTO assingToTrainer(String trainerName, String traineeEmail){
        if(traineeRepository.existsByEmail(traineeEmail)  &&  trainerRepository.existsByName(trainerName)){
            Trainee trainee = this.traineeRepository.findByEmail(traineeEmail);
            Trainer trainer = this.trainerRepository.findByName(trainerName);
            trainer.addTrainee(trainee);
            TrainerDTO trainerDTO = TrainerMapper.mapper.trainerToTrainerDTO(trainer);
            trainerDTO.setTrainee(trainer.getTrainee());
            return trainerDTO;
        } else {
            throw new RuntimeException("Trainee's email or Trainer's name not found");
        }
    }

    public Boolean changeTraineePassword(RequestTraineeDTO requestTraineeDTO){
        if(traineeRepository.existsByEmail(requestTraineeDTO.getEmail())){
            Trainee trainee = this.traineeRepository.findByEmail(requestTraineeDTO.getEmail());
            if(requestTraineeDTO.getPassword()==null|| requestTraineeDTO.getPassword().equals(requestTraineeDTO.getOldPassword())){
                throw new RuntimeException("New password is empty or same as the old password");
            }
            trainee.setPassword(requestTraineeDTO.getPassword());
            return true;
        }else {
            throw new RuntimeException("Trainee's email not found");
        }
    }

    public Boolean generateReport(String traineeEmail, String trainingCategory, Integer duration) {
        if(traineeRepository.existsByEmail(traineeEmail)){
            Trainee trainee = traineeRepository.findByEmail(traineeEmail);
            LocalDate localDate = LocalDate.now();
            ActivityReports activityReports = new ActivityReports(trainee.getID(), trainee.getTrainer().getID(), trainee.getName(), trainee.getTrainer().getName()
                    , localDate, trainingCategory, duration);

            Mono<Boolean> respuestaMono = webClient.post()
                    .uri("/api/add-report")
                    .bodyValue(activityReports)
                    .retrieve()
                    .bodyToMono(Boolean.class);
            return Boolean.TRUE.equals(respuestaMono.block());
        }else {
            throw new RuntimeException("Trainee's email not found");
        }
    }

    public ActivityReports getMonthlyReport(String email, String password){
        return null;
    }
}