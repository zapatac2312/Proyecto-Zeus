package com.demo.Service;

import com.demo.DTO.TrainerDTO;
import com.demo.DTO.TrainerMapper;
import com.demo.Modelo.Trainer;
import com.demo.Repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
            throw new RuntimeException("Invalid passwrod");
        }
        List<String> emailsBaseDatos = new ArrayList<>();
        this.trainerRepository.findAll().stream()
                .forEach(n -> emailsBaseDatos.add(n.getEmail()));

        if(emailsBaseDatos.contains(trainer.getEmail())){
            throw new RuntimeException("El correo electronico "+ trainer.getEmail()+" ya se encuentra registrado");
        }
        this.trainerRepository.save(trainer);
        return trainerDTO;
    }

    public TrainerDTO showTrainerInfo(String email){
        Trainer existingTrainer = trainerRepository.findByEmail(email);
        if (existingTrainer != null) {
            TrainerDTO trainerDTO = TrainerMapper.mapper.trainerToTrainerDTO(existingTrainer);
            trainerDTO.setTrainee(existingTrainer.getTrainee());
            return trainerDTO;
        } else {
            throw new RuntimeException("Trainer not found");
        }
    }

    public List<TrainerDTO> checkTrainerAvailability(){
        List<Trainer> traineerList = trainerRepository.findAll();
        return traineerList.stream()
                .filter(n-> n.getTrainerAvailability()<10)
                .map(TrainerMapper.mapper::trainerToTrainerDTO)
                .collect(Collectors.toList());
    }

    public Boolean changeTrainerPassword(String email, String oldPassword){
        return true;
    }

}
