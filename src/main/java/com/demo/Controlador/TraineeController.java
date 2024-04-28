package com.demo.Controlador;

import com.demo.DTO.RequestTraineeDTO;
import com.demo.DTO.TraineeDTO;
import com.demo.Modelo.Trainee;
import com.demo.Service.TraineeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trainee")
@Tag(name = "Usuarios - Users \uD83D\uDCAA")
public class TraineeController {
    private TraineeService traineeService;
    @Autowired
    public TraineeController(TraineeService traineeService) {
        this.traineeService = traineeService;
    }

    @Operation(method = "Añadir aprendiz\nAdd trainee",
            description = "Este end-point permite la inscripción de usuarios!\n" +
                        "\n" +
                        "This end-point allows users to sing in!",
                    responses = {
                    @ApiResponse(responseCode = "200", description = "Se hizo el registro correctamente\n"+
                            "\n"+
                            "Registration completed!"),
                    @ApiResponse(responseCode = "400", description = "Error en los datos ingresados\n"+
                            "\n"+
                            "Invalid information"),
                    @ApiResponse(responseCode = "500", description = "Error inesperado en el sistema\n"+
                            "\n"+
                            "Unexpected system error")}
                    )
    @PostMapping("/add")
    public TraineeDTO addTrainee(@RequestBody Trainee trainee){
        return this.traineeService.addTrainee(trainee);
    }
  @Operation(description = "Este end-point muestra la información basica del usuario\n"+
          "\n" +
          "This end-point shows the users basic information",
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
    public TraineeDTO showTraineeInfo(@RequestParam String email) {
        return traineeService.showTraineeInfo(email);
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
    public boolean changeTraineePassword(@RequestBody RequestTraineeDTO requestTraineeDTO) {
        return traineeService.changeTraineePassword(requestTraineeDTO);
    }
  @Operation(description = "Este end-point permite actualizar los datos del usuario\n"+
          "\n" +
          "This end-point updates the users information",
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
    public TraineeDTO updateTraineeInfo(@RequestParam String email, @RequestBody Trainee trainee) {
        return traineeService.updateTraineeInfo(email, trainee);
    }
 @Operation(description ="Este end-point asigna un entrenador a el usuario\n"+
         "\n" +
         "This end-point sets a trainer for the trainee",
         responses = {
                 @ApiResponse(responseCode = "200", description = "Asignación completa\n"+
                         "\n"+
                         "Assignment completed"),
                 @ApiResponse(responseCode = "400", description = "Error en los datos ingresados\n"+
                         "\n"+
                         "Invalid information"),
                 @ApiResponse(responseCode = "500", description = "Error inesperado en el sistema\n"+
                         "\n"+
                         "Unexpected system error")})
    @PutMapping("/assing-trainer")
        public Boolean assingToTrainer(@RequestParam String name, @RequestParam String traineeEmail){
        return this.traineeService.assingToTrainer(name, traineeEmail);
    }
 @Operation(description ="Este end-point genera un reporte del entrenamiento del día\n"+
         "\n" +
         "This end-point generates a report for today's training",
         responses = {
                 @ApiResponse(responseCode = "200", description = "Reporte generado exitosamente\n"+
                         "\n"+
                         "Report generated successfully"),
                 @ApiResponse(responseCode = "400", description = "Error en los datos ingresados\n"+
                         "\n"+
                         "Invalid information"),
                 @ApiResponse(responseCode = "500", description = "Error inesperado en el sistema\n"+
                         "\n"+
                         "Unexpected system error")})
    @PostMapping("/generate-report")
    public Boolean generateReport(@RequestParam String email, @RequestParam String trainingCategory, @RequestParam Integer duration){
        return this.traineeService.generateReport(email, trainingCategory, duration);
    }
 @Operation(description ="Este end-point genera un resumen de los entrenamientos del mes\n"+
         "\n" +
         "This end-point generates a summary for the monthly trainings",
         responses = {
                 @ApiResponse(responseCode = "200", description = "Resumen generado exitosamente\n"+
                         "\n"+
                         "Summary generated successfully"),
                 @ApiResponse(responseCode = "400", description = "Error en los datos ingresados\n"+
                         "\n"+
                         "Invalid information"),
                 @ApiResponse(responseCode = "500", description = "Error inesperado en el sistema\n"+
                         "\n"+
                         "Unexpected system error")})
    @GetMapping("/get-report")
    public String getReport(@RequestParam String traineeEmail, @RequestParam Integer month, @RequestParam Integer year ){
        return this.traineeService.getMonthlyReport(traineeEmail, month, year);
    }
}
