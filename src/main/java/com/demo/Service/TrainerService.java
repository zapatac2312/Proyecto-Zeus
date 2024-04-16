package com.demo.Service;

import com.demo.DTO.TraaineeMapper;
import com.demo.DTO.TraineeDTO;
import com.demo.DTO.TrainerDTO;
import com.demo.DTO.TrainerMapper;
import com.demo.Modelo.Trainer;
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

        Optional<Trainer> trainer = this.trainerRepository.findByEmailAndPassword(email, password);
        return null;
    }

    public Boolean changeTrainerPassword(String email, String oldPassword){
        return true;
    }

}
