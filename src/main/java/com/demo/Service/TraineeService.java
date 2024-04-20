package com.demo.Service;

import com.demo.DTO.TraineeDTO;
import com.demo.DTO.TraineeMapper;
import com.demo.DTO.TrainerDTO;
import com.demo.DTO.TrainerMapper;
import com.demo.Modelo.ActivityReports;
import com.demo.Modelo.Trainee;
import com.demo.Modelo.Trainer;
import com.demo.Modelo.TrainingCategories;
import com.demo.Repository.TraineeRepository;
import com.demo.Repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class TraineeService {

    private TraineeRepository traineeRepository;
    private TrainerRepository trainerRepository;

    @Autowired
    public TraineeService(TraineeRepository traineeRepository, TrainerRepository trainerRepository) {
        this.traineeRepository = traineeRepository;
        this.trainerRepository = trainerRepository;
    }

    public TraineeDTO addTrainee(Trainee trainee){
        TraineeDTO traineeDTO = TraineeMapper.mapper.traineeToTraineeDTO(trainee);
        if(trainee.getName()==null){
            throw new RuntimeException("A name is required");
        } else if(trainee.getEmail()==null){
            throw new RuntimeException("Invalid email");
        } else if(trainee.getPassword()==null){
            throw new RuntimeException("Invalid passwrod");
        }
        List<String> emailsBaseDatos = new ArrayList<>();
        this.traineeRepository.findAll().forEach(trainee1 -> emailsBaseDatos.add(trainee1.getEmail()));

        if(emailsBaseDatos.contains(trainee.getEmail())){
            throw new RuntimeException("El correo electronico "+ trainee.getEmail()+" ya se encuentra registrado");
        }
        this.traineeRepository.save(trainee);
        return traineeDTO;
    }

    public TraineeDTO updateTraineeInfo(String email,Trainee updatedTraineeInfo ) {
        Trainee existingTrainee = traineeRepository.findByEmail(email);
        if(updatedTraineeInfo.getName()==null){
            throw new RuntimeException("A name is required");
        } else if(updatedTraineeInfo.getEmail()==null){
            throw new RuntimeException("Invalid email");
        } else if(updatedTraineeInfo.getPassword()==null){
            throw new RuntimeException("Invalid passwrod");
        }
        List<String> emailsBaseDatos = new ArrayList<>();
        this.traineeRepository.findAll().forEach(trainee1 -> emailsBaseDatos.add(trainee1.getEmail()));
        for(String emailBD: emailsBaseDatos){
            if (Objects.equals(email, emailBD)){
                existingTrainee.setName(updatedTraineeInfo.getName());
                existingTrainee.setEmail(updatedTraineeInfo.getEmail());
                existingTrainee.setDateOfBirth(updatedTraineeInfo.getDateOfBirth());
                existingTrainee.setGender(updatedTraineeInfo.getGender());
                existingTrainee.setTrainingGoal(updatedTraineeInfo.getTrainingGoal());
                existingTrainee.setFitnessLevel(updatedTraineeInfo.getFitnessLevel());

                this.traineeRepository.save(existingTrainee);
                return TraineeMapper.mapper.traineeToTraineeDTO(updatedTraineeInfo);
            }
        }
        throw new RuntimeException("User not found");
    }

    public TraineeDTO showTraineeInfo(String email){
        Trainee existingTrainee = traineeRepository.findByEmail(email);

        List<String> emailsBaseDatos = new ArrayList<>();
        this.traineeRepository.findAll().forEach(trainee1 -> emailsBaseDatos.add(trainee1.getEmail()));

        for(String emailBD: emailsBaseDatos){
            if (Objects.equals(email, emailBD)){
                return TraineeMapper.mapper.traineeToTraineeDTO(existingTrainee);
            }
        }
        throw new RuntimeException("Trainee not found");
    }

    public TrainerDTO assingToTrainer(String trainerName, String traineeEmail){
        Trainee trainee = this.traineeRepository.findByEmail(traineeEmail);
        Trainer trainer = this.trainerRepository.findByName(trainerName);

        List<String> emailsDataBase = new ArrayList<>();
        this.traineeRepository.findAll().forEach(trainee1 -> emailsDataBase.add(trainee1.getEmail()));
        for(String emailBD: emailsDataBase){
            if (Objects.equals(traineeEmail, emailBD)){

                List<String> trainerNameDB = new ArrayList<>();
                this.trainerRepository.findAll().forEach(trainer1 -> trainerNameDB.add(trainer1.getEmail()));
                for(String nameDB: trainerNameDB){
                    if (Objects.equals(nameDB, trainerName)){

                        trainer.addTrainee(trainee);
                        TrainerDTO trainerDTO = TrainerMapper.mapper.trainerToTrainerDTO(trainer);
                        trainerDTO.setTrainee(trainer.getTrainee());
                        return trainerDTO;
                    } else {
                        throw new RuntimeException("Trainer's name not found");
                    }
                }
            }else {
                throw new RuntimeException("Trainee's email not found");
            }
        }
       return new TrainerDTO();
    }

    public Boolean changeTraineePassword(String email, String oldPassword){
        return true;
    }

    public ActivityReports addTraining(String email, TrainingCategories trainingCategories, Date date, Trainer trainer) {
        return null;
    }

    public ActivityReports getMonthlyReport(String email, String password){
        return null;
    }
}