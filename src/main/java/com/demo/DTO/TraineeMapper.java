package com.demo.DTO;

import com.demo.Modelo.Trainee;
import com.demo.Modelo.Trainer;
import jakarta.persistence.Entity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TraineeMapper {

    TraineeMapper mapper = Mappers.getMapper(TraineeMapper.class);

    Trainee traineeDTOtoTrainee(TraineeDTO traineeDTO);

    TraineeDTO traineeToTraineeDTO(Trainee trainee);
}