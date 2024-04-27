package com.demo.Controlador;
import com.demo.DTO.RequestTrainerDTO;
import com.demo.DTO.TrainerDTO;
import com.demo.Modelo.Trainer;
import com.demo.Service.TrainerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Entrenadores- Trainers ⚡")
@RequestMapping("/trainer")
public class TrainerController {
    private final TrainerService trainerService;
    @Autowired
    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }
 @Operation(description = "Este end-point permite la inscripción de entrenadores!\n" +
         "\n" +
         "This end-point allows trainers to sing in!",
         responses = {
                 @ApiResponse(responseCode = "200", description = "Se hizo el registro correctamente\n"+
                         "\n"+
                         "Registration completed!"),
                 @ApiResponse(responseCode = "400", description = "Error en los datos ingresados\n"+
                         "\n"+
                         "Invalid information"),
                 @ApiResponse(responseCode = "500", description = "Error inesperado en el sistema\n"+
                         "\n"+
                         "Unexpected system error")})
    @PostMapping("/add")
    public TrainerDTO addTrainer(@RequestBody Trainer trainer) {
        return trainerService.addTrainer(trainer);
    }
 @Operation(description = "Este end-point muestra la información basica del entrenador\n"+
         "\n" +
         "This end-point shows the trainers basic information",
         responses = {
                 @ApiResponse(responseCode = "200", description = "Información del usuario\n"+
                         "\n"+
                         "User information"),
                 @ApiResponse(responseCode = "400", description = "Error en los datos ingresados\n"+
                         "\n"+
                         "Invalid information"),
                 @ApiResponse(responseCode = "500", description = "Error inesperado en el sistema\n"+
                         "\n"+
                         "Unexpected system error")})
    @GetMapping("/info")
    public TrainerDTO showTrainerInfo(@RequestParam String email) {
        return trainerService.showTrainerInfo(email);
    }
  @Operation(description = "Este end-point permite el cambio de contraseña\n"+
          "\n" +
          "This end-point allows to change the password",
          responses = {
                  @ApiResponse(responseCode = "200", description = "Nueva contraseña creada exitosamente\n"+
                          "\n"+
                          "New password created successfully"),
                  @ApiResponse(responseCode = "400", description = "Error en los datos ingresados\n"+
                          "\n"+
                          "Invalid information"),
                  @ApiResponse(responseCode = "500", description = "Error inesperado en el sistema\n"+"\n"+
                          "Unexpected system error")})
    @PutMapping("/change-password")
    public boolean changeTrainerPassword(@RequestBody RequestTrainerDTO requestTrainerDTO) {
        return trainerService.changeTrainerPassword(requestTrainerDTO);
    }
   @Operation(description = "Este end-point permite ver la disponibilidad de los entrenadores\n"+
           "\n" +
           "This end-point shows trainers availability",
           responses = {
                   @ApiResponse(responseCode = "200", description = "Esta es la disponibilidad\n"+
                           "\n"+
                           "This is the availability"),
                   @ApiResponse(responseCode = "400", description = "Error en los datos ingresados\n"+
                           "\n"+
                           "Invalid information"),
                   @ApiResponse(responseCode = "Error inesperado en el sistema\n"+
                           "\n"+
                           "Unexpected system error")})
    @GetMapping("/availability")
    public List<TrainerDTO> checkTrainerAvailability() {
        return trainerService.checkTrainerAvailability();
    }
 @Operation(description = "Este end-point permite actualizar los datos del entrenador\n"+
         "\n" +
         "This end-point updates the trainers information",
         responses = {
                 @ApiResponse(responseCode = "200", description = "Error en los datos ingresados\n"+
                         "\n"+
                         "Invalid information"),
                 @ApiResponse(responseCode = "400", description = "Error en los datos ingresados\n"+
                         "\n"+
                         "Invalid information"),
                 @ApiResponse(responseCode = "500", description = "Error inesperado en el sistema\n"+
                         "\n"+
                         "Unexpected system error")})
    @PutMapping("/update-info")
    public TrainerDTO updateTrainerInfo(@RequestParam String email, @RequestBody Trainer trainer) {
        return trainerService.updateTrainerInfo(email, trainer);
    }
}
