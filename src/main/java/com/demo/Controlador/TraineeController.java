package com.demo.Controlador;

import com.demo.DTO.TraineeDTO;
import com.demo.Modelo.Trainee;
import com.demo.Service.TraineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trainee")
public class TraineeController {

    private TraineeService traineeService;

    @Autowired
    public TraineeController(TraineeService traineeService) {
        this.traineeService = traineeService;
    }

    @PostMapping("/add")
    public TraineeDTO addTrainee(@RequestBody Trainee trainee){
        return this.traineeService.addTrainee(trainee);
    }

}
