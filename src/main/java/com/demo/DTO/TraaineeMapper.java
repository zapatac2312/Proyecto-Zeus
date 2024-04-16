package com.demo.DTO;

import com.demo.Modelo.Trainee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TraaineeMapper {

    TraaineeMapper mapper = Mappers.getMapper(TraaineeMapper.class);

    Trainee traineeDTOtoTrainee(TraineeDTO traineeDTO);

    TraineeDTO traineeToTraineeDTO(Trainee trainee);

}
