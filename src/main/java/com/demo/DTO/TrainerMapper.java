package com.demo.DTO;

import com.demo.Modelo.Trainee;
import com.demo.Modelo.Trainer;
import jakarta.persistence.Entity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TrainerMapper {

    TrainerMapper mapper = Mappers.getMapper(TrainerMapper.class);

    Trainee trainerDTOtoTrainer(TrainerDTO trainerDTO);

    TrainerDTO trainerToTrainerDTO(Trainer trainer);

}