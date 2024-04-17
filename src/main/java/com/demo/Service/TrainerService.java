package com.demo.Service;

import com.demo.DTO.TraineeMapper;
import com.demo.DTO.TrainerDTO;
import com.demo.DTO.TrainerMapper;
import com.demo.Modelo.Trainee;
import com.demo.Modelo.Trainer;
import com.demo.Repository.TraineeRepository;
import com.demo.Repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrainerService {
    private TrainerRepository trainerRepository;

    @Autowired
    public TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    public TrainerDTO addTrainer(Trainer trainer){
        TrainerDTO trainerDTO = TrainerMapper.mapper.trainerToTrainerDTO(trainer);
        this.trainerRepository.save(trainer);
        return trainerDTO;
    }

    public TrainerDTO showTrainerInfo(String email, String password){

        Trainer existingTrainer = trainerRepository.findByEmailAndPassword(email, password);
        if (existingTrainer != null) {
            return TrainerMapper.mapper.trainerToTrainerDTO(existingTrainer);

        } else {
            throw new RuntimeException("Trainer not found");
        }
    }




    public Boolean changeTrainerPassword(String email, String oldPassword){
        return true;
    }

}
