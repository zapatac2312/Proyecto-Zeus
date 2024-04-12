package com.demo.Service;

import com.demo.DTO.TrainerDTO;
import com.demo.Modelo.Trainer;
import com.demo.Repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainerService {
    private TrainerRepository trainerRepository;

    @Autowired
    public TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    public TrainerDTO addTrainer(Trainer trainer){
        return null;
    }

    public TrainerDTO showTrainerInfo(String email, String password){
        return null;
    }

    public Boolean changeTrainerPassword(String email, String oldPassword){
        return true;
    }

}
