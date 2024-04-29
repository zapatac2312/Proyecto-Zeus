package com.demo.Controlador;

import com.demo.ApiExceptions.InvalidTrainee;
import com.demo.DTO.RequestTraineeDTO;
import com.demo.DTO.ResponseDTO;
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
    public ResponseDTO<TraineeDTO> addTrainee(@RequestBody Trainee trainee) {

        try {
            return new ResponseDTO(this.traineeService.addTrainee(trainee));
        } catch (InvalidTrainee e) {
            return new ResponseDTO(e.getMessage());
        }
    }

    @GetMapping("/info")
    public ResponseDTO<TraineeDTO> showTraineeInfo(@RequestParam String email) {
        try {
            return new ResponseDTO(this.traineeService.showTraineeInfo(email));
        } catch (InvalidTrainee e) {
            return new ResponseDTO(e.getMessage());
        }
    }

    @PutMapping("/password")
    public ResponseDTO<Boolean> changeTraineePassword(@RequestBody RequestTraineeDTO requestTraineeDTO) {
        try {
            return new ResponseDTO(this.traineeService.changeTraineePassword(requestTraineeDTO));

        } catch (InvalidTrainee e) {
            return new ResponseDTO(e.getMessage());
        }
    }

    @PutMapping("/info")
    public ResponseDTO<TraineeDTO> updateTraineeInfo(@RequestParam String email, @RequestBody Trainee trainee) {
        try {
            return new ResponseDTO(this.traineeService.updateTraineeInfo(email, trainee));
        } catch (InvalidTrainee e) {
            return new ResponseDTO(e.getMessage());
        }
    }

    @PutMapping("/trainer")
    public ResponseDTO<TraineeDTO> assingToTrainer(@RequestParam String name, @RequestParam String traineeEmail) {
        try {
            return new ResponseDTO(this.traineeService.assingToTrainer(name, traineeEmail));

        } catch (InvalidTrainee e) {
            return new ResponseDTO(e.getMessage());
        }
    }


    @PostMapping("/report")
    private Boolean generateReport(@RequestParam String email, @RequestParam String trainingCategory, @RequestParam Integer duration){
        return this.traineeService.generateReport(email, trainingCategory, duration);
    }
}
