package com.demo.Controlador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trainers")
public class TrainerController {
    private final TrainerService trainerService;

    @Autowired
    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @PostMapping("/add")
    public TrainerResponseDTO addTrainer(@RequestBody Trainer trainer) {
        return trainerService.addTrainer(trainer);
    }

    @GetMapping("/info")
    public TrainerResponseDTO showTrainerInfo(@RequestParam String email, @RequestParam String password) {
        return trainerService.showTrainerInfo(email, password);
    }

    @PutMapping("/change-password")
    public boolean changeTrainerPassword(@RequestParam String email, @RequestParam String oldPassword) {
        return trainerService.changeTrainerPassword(email, oldPassword);
    }
}
