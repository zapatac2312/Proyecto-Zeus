package com.demo.Service;

import com.demo.DTO.*;
import com.demo.Modelo.Trainee;
import com.demo.Modelo.Trainer;
import com.demo.Repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TrainerService {
    private TrainerRepository trainerRepository;

    @Autowired
    public TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    public TrainerDTO addTrainer(Trainer trainer){
        TrainerDTO trainerDTO = TrainerMapper.mapper.trainerToTrainerDTO(trainer);
        trainerDTO.setTrainee(trainer.getTrainee());
        if(trainer.getName()==null){
            throw new RuntimeException("A name is required");
        } else if(trainer.getEmail()==null){
            throw new RuntimeException("Invalid email");
        } else if(trainer.getPassword()==null){
            throw new RuntimeException("Invalid password");
        }
        List<String> emailsDataBase = new ArrayList<>();
        this.trainerRepository.findAll()
                .forEach(trainer1 -> emailsDataBase.add(trainer1.getEmail()));

        if(emailsDataBase.contains(trainer.getEmail())){
            throw new RuntimeException("Trainees email: "+ trainer.getEmail()+" already registered");
        }
        this.trainerRepository.save(trainer);
        return trainerDTO;
    }
    public TrainerDTO updateTrainerInfo(String email,Trainer updatedTrainerInfo) {

        List<String> emailsDataBase = new ArrayList<>();
        this.trainerRepository.findAll().forEach(trainer1 -> emailsDataBase.add(trainer1.getEmail()));
        if(!emailsDataBase.contains(email)){
            throw new RuntimeException("The trainees information you want to update doesn't exist in our system");
        }
        Trainer trainer = trainerRepository.findByEmail(email);
        if(updatedTrainerInfo.getName()==null){
            throw new RuntimeException("A name is required");
        } else if(updatedTrainerInfo.getEmail()==null){
            throw new RuntimeException("Invalid email");
        } else if(updatedTrainerInfo.getPassword()==null){
            throw new RuntimeException("Invalid password");
        }
        for(String emailBD: emailsDataBase){
            if (Objects.equals(email, emailBD)){
                trainer.setName(updatedTrainerInfo.getName());
                trainer.setEmail(updatedTrainerInfo.getEmail());
                trainer.setSpeciality(updatedTrainerInfo.getSpeciality());
                trainer.setExperience(updatedTrainerInfo.getExperience());
                trainer.setCertifications(updatedTrainerInfo.getCertifications());

                this.trainerRepository.save(trainer);
                return TrainerMapper.mapper.trainerToTrainerDTO(trainer);
            }
        }
        throw new RuntimeException("User not found");
    }


    public TrainerDTO showTrainerInfo(String email){

        List<String> emailsDataBase = new ArrayList<>();
        this.trainerRepository.findAll().forEach(trainer1 -> emailsDataBase.add(trainer1.getEmail()));
        if(!emailsDataBase.contains(email)){
            throw new RuntimeException("Trainer's email not found");
        }
        Trainer trainer = trainerRepository.findByEmail(email);
        for(String emailBD: emailsDataBase){
            if (Objects.equals(email, emailBD)){
                return TrainerMapper.mapper.trainerToTrainerDTO(trainer);
            }
        }
        throw new RuntimeException("Trainer not found");
    }
    public List<TrainerDTO> checkTrainerAvailability(){
        List<Trainer> traineerList = trainerRepository.findAll();
        return traineerList.stream()
                .filter(n-> n.getTrainerAvailability()<10)
                .map(TrainerMapper.mapper::trainerToTrainerDTO)
                .collect(Collectors.toList());
    }

    public Boolean changeTrainerPassword(RequestTrainerDTO requestTrainerDTO){
        List<String> emailsDataBase = new ArrayList<>();
        this.trainerRepository.findAll().forEach(trainee1 -> emailsDataBase.add(trainee1.getEmail()));
        if(!emailsDataBase.contains(requestTrainerDTO.getEmail())){
            throw new RuntimeException("Trainer's email not found");
        }
        Trainer trainer = this.trainerRepository.findByEmail(requestTrainerDTO.getEmail());
        if(requestTrainerDTO.getPassword()==null|| requestTrainerDTO.getPassword().equals(requestTrainerDTO.getOldPassword())){
            throw new RuntimeException("New password is empty or same as the old password");
        }
        trainer.setPassword(requestTrainerDTO.getPassword());
        return true;
    }
}