package com.demo.Controlador;
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
    public boolean changeTrainerPassword(@RequestParam String email, @RequestParam String oldPassword) {
        return trainerService.changeTrainerPassword(email, oldPassword);
    }

    @GetMapping("/availability")
    public List<TrainerDTO> checkTrainerAvailability() {
        return trainerService.checkTrainerAvailability();
    }

    //asignar aprendiz

    /*@PutMapping("/update-info")
    public TrainerDTO updateTrainerInfo(@RequestParam String email, @RequestParam String password, @RequestBody Trainee trainee) {

        return trainerService.updateTrainerInfo(email, password,trainee);

    }*/
}
