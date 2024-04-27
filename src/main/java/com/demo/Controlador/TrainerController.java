package com.demo.Controlador;
import com.demo.DTO.RequestTraineeDTO;
import com.demo.DTO.RequestTrainerDTO;
import com.demo.DTO.TraineeDTO;
import com.demo.DTO.TrainerDTO;
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
    public TrainerDTO addTrainer(@RequestBody Trainer trainer) {
        return trainerService.addTrainer(trainer);
    }
    @GetMapping("/info")
    public TrainerDTO showTrainerInfo(@RequestParam String email) {
        return trainerService.showTrainerInfo(email);
    }
    @PutMapping("/change-password")
    public boolean changeTrainerPassword(@RequestBody RequestTrainerDTO requestTrainerDTO) {
        return trainerService.changeTrainerPassword(requestTrainerDTO);
    }
    @GetMapping("/availability")
    public List<TrainerDTO> checkTrainerAvailability() {
        return trainerService.checkTrainerAvailability();
    }
    @PutMapping("/update-info")
    public TrainerDTO updateTrainerInfo(@RequestParam String email, @RequestBody Trainer trainer) {
        return trainerService.updateTrainerInfo(email, trainer);
    }
}
