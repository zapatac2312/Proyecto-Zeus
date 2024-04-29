package com.demo.Controlador;
import com.demo.ApiExceptions.InvalidTrainee;
import com.demo.ApiExceptions.InvalidTrainer;
import com.demo.DTO.*;
import com.demo.Modelo.Trainee;
import com.demo.Modelo.Trainer;
import com.demo.Service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainer")
public class TrainerController {
    private final TrainerService trainerService;

    @Autowired
    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @PostMapping("/add")
    public ResponseDTO<TrainerDTO> addTrainer(@RequestBody Trainer trainer) {
        try {
            return new ResponseDTO(this.trainerService.addTrainer(trainer));

        } catch (InvalidTrainer e) {
            return new ResponseDTO(e.getMessage());
        }

    }

    @GetMapping("/info")
    public  ResponseDTO<TrainerDTO> showTrainerInfo(@RequestParam String email) {
        try {
            return new ResponseDTO(this.trainerService.showTrainerInfo(email));
        } catch (InvalidTrainer e) {
            return new ResponseDTO(e.getMessage());
        }

    }

    @PutMapping("/password")
    public ResponseDTO<Boolean> changeTrainerPassword(@RequestBody RequestTrainerDTO requestTrainerDTO) {
        try {
            return new ResponseDTO(this.trainerService.changeTrainerPassword(requestTrainerDTO));

        } catch (InvalidTrainer e) {
            return new ResponseDTO(e.getMessage());
        }
    }

    @GetMapping("/availability")
    public ResponseDTO<List<TrainerDTO>> checkTrainerAvailability() {
        return new ResponseDTO(trainerService.checkTrainerAvailability());
    }


    @PutMapping("/info")
    public ResponseDTO<TrainerDTO> updateTrainerInfo(@RequestParam String email, @RequestBody Trainer trainer) {
        try {
            return new ResponseDTO(this.trainerService.updateTrainerInfo(email, trainer));

        } catch (InvalidTrainer e) {
            return new ResponseDTO(e.getMessage());
        }
    }
}
