package com.demo.Controlador;

import com.demo.DTO.RequestTraineeDTO;
import com.demo.DTO.TraineeDTO;
import com.demo.DTO.TrainerDTO;
import com.demo.Modelo.ActivityReports;
import com.demo.Modelo.Trainee;
import com.demo.Service.TraineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public boolean changeTraineePassword(@RequestBody RequestTraineeDTO requestTraineeDTO) {
        return traineeService.changeTraineePassword(requestTraineeDTO);
    }
    @PutMapping("/update-info")
    public TraineeDTO updateTraineeInfo(@RequestParam String email, @RequestBody Trainee trainee) {
        return traineeService.updateTraineeInfo(email, trainee);
    }
    @PutMapping("/assing-trainer")
        public Boolean assingToTrainer(@RequestParam String name, @RequestParam String traineeEmail){
        return this.traineeService.assingToTrainer(name, traineeEmail);
    }
    @PostMapping("/generate-report")
    public Boolean generateReport(@RequestParam String email, @RequestParam String trainingCategory, @RequestParam Integer duration){
        return this.traineeService.generateReport(email, trainingCategory, duration);
    }
    @GetMapping("/get-report")
    public String getReport(@RequestParam String traineeEmail, @RequestParam Integer month, @RequestParam Integer year ){
        return this.traineeService.getMonthlyReport(traineeEmail, month, year);
    }
}
