package com.demo.Controlador;

import com.demo.DTO.TraineeDTO;
import com.demo.DTO.TrainerDTO;
import com.demo.Modelo.Trainee;
import com.demo.Service.TraineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/info")
    public TraineeDTO showTraineeInfo(@RequestParam String email) {
        return traineeService.showTraineeInfo(email);
    }

    @PutMapping("/change-password")
    public boolean changeTraineePassword(@RequestParam String email, @RequestParam String oldPassword) {
        return traineeService.changeTraineePassword(email, oldPassword);
    }

    @PutMapping("/update-info")
    public TraineeDTO updateTraineeInfo(@RequestParam String email, @RequestBody Trainee trainee) {
        return traineeService.updateTraineeInfo(email, trainee);
    }

    @PutMapping("/assing-trainer")
    public TrainerDTO assingToTrainer(@RequestParam String name, @RequestParam String traineeEmail){
        return this.traineeService.assingToTrainer(name, traineeEmail);
    }

}
