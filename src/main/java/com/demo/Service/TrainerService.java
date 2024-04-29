package com.demo.Service;

import com.demo.ApiExceptions.BusinessException;
import com.demo.ApiExceptions.InvalidTrainer;
import com.demo.DTO.*;
import com.demo.Modelo.Trainee;
import com.demo.Modelo.Trainer;
import com.demo.Repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        if(trainer.getName()==null || trainer.getName().isEmpty() ){
            throw new InvalidTrainer("P-400", "A name is required");
        } else if(trainer.getEmail()==null|| trainer.getEmail().isEmpty()){
            throw new InvalidTrainer("P-400","Invalid email");
        } else if(trainer.getPassword()==null|| trainer.getPassword().isEmpty()){
            throw new InvalidTrainer("P-400","Invalid password");
        }
        if(trainerRepository.existsByEmail(trainer.getEmail())){
            this.trainerRepository.save(trainer);
            return trainerDTO;
        }else {
            throw new BusinessException("P-500","Trainer's email: "+ trainer.getEmail()+" already registered",HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }
    public TrainerDTO updateTrainerInfo(String email,Trainer updatedTrainerInfo) {
        if(trainerRepository.existsByEmail(email)){
            Trainer trainer = trainerRepository.findByEmail(email);
            if(updatedTrainerInfo.getName()==null || updatedTrainerInfo.getName().isEmpty()){
                throw new InvalidTrainer("P-400","A name is required");
            } else if(updatedTrainerInfo.getEmail()==null || updatedTrainerInfo.getEmail().isEmpty() ){
                throw new InvalidTrainer("P-400","Invalid email");
            } else if(updatedTrainerInfo.getPassword()==null || updatedTrainerInfo.getPassword().isEmpty()){
                throw new InvalidTrainer("P-400","Invalid password");
            }
            trainer.setName(updatedTrainerInfo.getName());
            trainer.setEmail(updatedTrainerInfo.getEmail());
            trainer.setSpeciality(updatedTrainerInfo.getSpeciality());
            trainer.setExperience(updatedTrainerInfo.getExperience());
            trainer.setCertifications(updatedTrainerInfo.getCertifications());
            trainer.setTrainee(updatedTrainerInfo.getTrainee());
            this.trainerRepository.save(trainer);
            return TrainerMapper.mapper.trainerToTrainerDTO(trainer);
        }else {
            throw new BusinessException("P-500","The trainer's information you want to update doesn't exist in our system",HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }
    public TrainerDTO showTrainerInfo(String email){
        if(trainerRepository.existsByEmail(email)){
            Trainer trainer = trainerRepository.findByEmail(email);
            return TrainerMapper.mapper.trainerToTrainerDTO(trainer);
        }else {
            throw new BusinessException("P-500","Trainer's email not found",HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }

    public List<TrainerDTO> checkTrainerAvailability(){
        List<Trainer> traineerList = trainerRepository.findAll();
        return traineerList.stream()
                .filter(n-> n.getTrainerAvailability()<10)
                .map(TrainerMapper.mapper::trainerToTrainerDTO)
                .collect(Collectors.toList());
    }

    public Boolean changeTrainerPassword(RequestTrainerDTO requestTrainerDTO){
        if(trainerRepository.existsByEmail((requestTrainerDTO.getEmail()))){
            Trainer trainer = this.trainerRepository.findByEmail(requestTrainerDTO.getEmail());
            if(requestTrainerDTO.getPassword()==null || requestTrainerDTO.getPassword().isEmpty() || requestTrainerDTO.getPassword().equals(requestTrainerDTO.getOldPassword())){
                throw new InvalidTrainer("P-400","New password is empty or same as the old password");
            }
            trainer.setPassword(requestTrainerDTO.getPassword());
            return true;
        }else {
            throw new BusinessException("P-500","Trainer's email not found", HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }
}