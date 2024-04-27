package com.demo;

import com.demo.DTO.*;
import com.demo.Modelo.ActivityReports;
import com.demo.Modelo.Trainee;
import com.demo.Modelo.Trainer;
import com.demo.Repository.TraineeRepository;
import com.demo.Repository.TrainerRepository;
import com.demo.Service.TraineeService;
import com.demo.Service.TrainerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TraineeServiceTest {

    private TraineeRepository traineeRepository;
    private TrainerRepository trainerRepository;
    private TrainerService trainerService;
    private TraineeService traineeService;

    @MockBean
    private WebClient webClient;

    @BeforeEach
    public void setUp() {
        traineeRepository = mock(TraineeRepository.class);
        trainerRepository = mock(TrainerRepository.class);
        trainerService = new TrainerService(trainerRepository);
        traineeService = new TraineeService(traineeRepository, trainerRepository, trainerService, webClient);
    }

    //arrange
    //act
    //assert

    @Test
    public void addPatientWhenNameIsNull(){
        // Arrange
        Trainee trainee = new Trainee();
        trainee.setName(null);
        trainee.setEmail("null@gmail.com");
        trainee.setPassword("123");
        // Act and Assert
        assertThrows(RuntimeException.class, () -> this.traineeService.addTrainee(trainee));
    }

    @Test
    public void addPatientWhenEmailIsNull(){
        // Arrange
        Trainee trainee = new Trainee();
        trainee.setName("null");
        trainee.setEmail(null);
        trainee.setPassword("null");
        // Act and Assert
        assertThrows(RuntimeException.class, () -> this.traineeService.addTrainee(trainee));
    }

    @Test
    public void addPatientWhenPasswordIsNull(){
        // Arrange
        Trainee trainee = new Trainee();
        trainee.setName("null");
        trainee.setEmail("null");
        trainee.setPassword(null);
        // Act and Assert
        assertThrows(RuntimeException.class, () -> this.traineeService.addTrainee(trainee));
    }

    @Test
    void addTraineeWhenTraineeAlreadyExistsThrowsException() {
        // Arrange
        Trainee trainee = new Trainee();
        trainee.setName("John Doe");
        trainee.setEmail("john.doe@example.com");
        trainee.setPassword("password");
        // Mocking the behavior of existByEmail to return true, indicating the email already exists
        when(traineeRepository.existsByEmail(trainee.getEmail())).thenReturn(true);
        // Act and Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            traineeService.addTrainee(trainee);
        });
        assertEquals("Trainees email: john.doe@example.com already registered", exception.getMessage());
    }
    @Test
    void addTraineeWithoutException() {
        // Arrange
        Trainee trainee = new Trainee();
        trainee.setName("John Doe");
        trainee.setEmail("john.doe@example.com");
        trainee.setPassword("password");
        // Mocking
        when(traineeRepository.existsByEmail(trainee.getEmail())).thenReturn(false);
        when(traineeRepository.save(trainee)).thenReturn(trainee);
        TraineeDTO traineeDTO = TraineeMapper.mapper.traineeToTraineeDTO(trainee);
        // Act and Assert
        assertEquals(traineeDTO,traineeService.addTrainee(trainee));
    }
    @Test
    public void updateTraineeInfoWhenEmailDoesntExists(){
        // Arrange
        Trainee trainee = new Trainee();
        trainee.setEmail("john.doe@example.com");
        Trainee updatedTraineeInfo = new Trainee();
        // Mocking
        when(traineeRepository.existsByEmail(trainee.getEmail())).thenReturn(false);
        // Act and Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            traineeService.updateTraineeInfo(trainee.getEmail(), updatedTraineeInfo);
        });
        assertEquals("The trainees information you want to update doesn't exist in our system", exception.getMessage());
    }

    @Test
    public void updateTraineeInfoWhenNameIsNull(){
        // Arrange
        Trainee trainee = new Trainee();
        trainee.setEmail("john.doe@example.com");
        Trainee updatedTraineeInfo = new Trainee();
        updatedTraineeInfo.setName(null);
        // Mocking
        when(traineeRepository.existsByEmail(trainee.getEmail())).thenReturn(true);
        // Act and Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            traineeService.updateTraineeInfo(trainee.getEmail(), updatedTraineeInfo);
        });
        assertEquals("A name is required", exception.getMessage());
    }

    @Test
    public void updateTraineeInfoWhenEmailIsNull(){
        // Arrange
        Trainee trainee = new Trainee();
        trainee.setEmail("john.doe@example.com");
        Trainee updatedTraineeInfo = new Trainee();
        updatedTraineeInfo.setName("null");
        updatedTraineeInfo.setEmail(null);
        // Mocking
        when(traineeRepository.existsByEmail(trainee.getEmail())).thenReturn(true);
        // Act and Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            traineeService.updateTraineeInfo(trainee.getEmail(), updatedTraineeInfo);
        });
        assertEquals("Invalid email", exception.getMessage());
    }

    @Test
    public void updateTraineeInfoWhenPasswordIsNull(){
        // Arrange
        Trainee trainee = new Trainee();
        trainee.setEmail("john.doe@example.com");
        Trainee updatedTraineeInfo = new Trainee();
        updatedTraineeInfo.setName("null");
        updatedTraineeInfo.setEmail("null");
        updatedTraineeInfo.setPassword(null);
        // Mocking
        when(traineeRepository.existsByEmail(trainee.getEmail())).thenReturn(true);
        // Act and Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            traineeService.updateTraineeInfo(trainee.getEmail(), updatedTraineeInfo);
        });
        assertEquals("Invalid password", exception.getMessage());
    }

    @Test
    void updateTraineeInfoTraineeExistsSaveSuccessfully() {
        // Arrange
        String email = "john.doe@example.com";
        Trainee updatedTraineeInfo = new Trainee();
        updatedTraineeInfo.setName("John Doe");
        updatedTraineeInfo.setEmail(email);
        updatedTraineeInfo.setPassword("newPassword");
        updatedTraineeInfo.setDateOfBirth("1990-01-01");
        updatedTraineeInfo.setGender("Male");
        updatedTraineeInfo.setTrainingGoal("Lose weight");
        updatedTraineeInfo.setFitnessLevel("Intermediate");
        Trainee existingTrainee = new Trainee();
        existingTrainee.setEmail(email);
        // Mocking the behavior of existByEmail to return true, indicating the trainee exists
        when(traineeRepository.existsByEmail(email)).thenReturn(true);
        when(traineeRepository.findByEmail(email)).thenReturn(existingTrainee);
        // Act
        TraineeDTO result = traineeService.updateTraineeInfo(email, updatedTraineeInfo);
        // Assert
        assertNotNull(result);
        assertEquals(updatedTraineeInfo.getName(), existingTrainee.getName());
        assertEquals(updatedTraineeInfo.getEmail(), existingTrainee.getEmail());
        assertEquals(updatedTraineeInfo.getDateOfBirth(), existingTrainee.getDateOfBirth());
        assertEquals(updatedTraineeInfo.getGender(), existingTrainee.getGender());
        assertEquals(updatedTraineeInfo.getTrainingGoal(), existingTrainee.getTrainingGoal());
        assertEquals(updatedTraineeInfo.getFitnessLevel(), existingTrainee.getFitnessLevel());
    }

    @Test
    public void showTraineeInfoWhenEmailNotFound(){
        // Arrange
        Trainee trainee = new Trainee();
        trainee.setEmail("john.doe@example.com");
        // Mocking
        when(traineeRepository.existsByEmail(trainee.getEmail())).thenReturn(false);
        // Act and Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            traineeService.showTraineeInfo(trainee.getEmail());
        });
        assertEquals("Trainee's email not found", exception.getMessage());
    }

    @Test
    public void showTraineeInfoWithNoException(){
        // Arrange
        Trainee trainee = new Trainee();
        trainee.setName("John Doe");
        trainee.setEmail("john.doe@example.com");
        trainee.setPassword("password");
        // Mocking
        when(traineeRepository.existsByEmail(trainee.getEmail())).thenReturn(true);
        when(traineeRepository.findByEmail(trainee.getEmail())).thenReturn(trainee);
        TraineeDTO traineeDTO = TraineeMapper.mapper.traineeToTraineeDTO(trainee);
        // Act and Assert
        assertEquals(traineeDTO, traineeService.showTraineeInfo(trainee.getEmail()));
    }

    @Test
    public void assingTrainerWhenEmailDoesntExist(){
        // Arrange
        Trainee trainee = new Trainee();
        trainee.setEmail("john.doe@example.com");
        Trainer trainer = new Trainer();
        trainer.setName("john");
        // Mocking
        when(traineeRepository.existsByEmail(trainee.getEmail())).thenReturn(false);
        // Act and Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            traineeService.assingToTrainer(trainee.getEmail(), trainer.getName());
        });
        assertEquals("Trainee's email or Trainer's name not found", exception.getMessage());
    }


    @Test
    public void changeTraineePasswordWhenEmailDoesntExist(){
        // Arrange
        RequestTraineeDTO trainee = new RequestTraineeDTO();
        trainee.setEmail("john.doe@example.com");
        // Mocking
        when(traineeRepository.existsByEmail(trainee.getEmail())).thenReturn(false);
        // Act and Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            traineeService.changeTraineePassword(trainee);
        });
        assertEquals("Trainee's email not found", exception.getMessage());
    }

    @Test
    public void changeTraineePasswordWhenPasswordIsNull(){
        RequestTraineeDTO trainee = new RequestTraineeDTO();
        trainee.setEmail("john.doe@example.com");
        trainee.setName("John Doe");
        trainee.setPassword(null);
        // Mocking
        when(traineeRepository.existsByEmail(trainee.getEmail())).thenReturn(true);
        when(traineeRepository.findByEmail(anyString())).thenReturn(any());
        // Act and Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            traineeService.changeTraineePassword(trainee);
        });
        assertEquals("New password is empty or same as the old password", exception.getMessage());
    }

    @Test
    public void changeTraineePasswordWithNoException(){
        RequestTraineeDTO requestTraineeDTO = new RequestTraineeDTO();
        requestTraineeDTO.setEmail("john.doe@example.com");
        requestTraineeDTO.setName("John Doe");
        requestTraineeDTO.setPassword("null");
        Trainee trainee1 = new Trainee();
        trainee1.setName("John Doe");
        trainee1.setEmail("john.doe@example.com");
        trainee1.setPassword("password");
        // Mocking
        when(traineeRepository.existsByEmail(requestTraineeDTO.getEmail())).thenReturn(true);
        when(traineeRepository.findByEmail(requestTraineeDTO.getEmail())).thenReturn(trainee1);
        // Act and Assert
        assertTrue(() -> traineeService.changeTraineePassword(requestTraineeDTO));
    }

    @Test
    public void testGenerateReportTraineeEmailNotFound() {
        // Arrange
        String traineeEmail = "trainee@example.com";
        String trainingCategory = "Programming";
        Integer duration = 60;
        when(traineeRepository.existsByEmail(traineeEmail)).thenReturn(false);
        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                traineeService.generateReport(traineeEmail, trainingCategory, duration));
        assertEquals("Trainee's email not found", exception.getMessage());
    }
    @Test
    public void testGetMonthlyReportTraineeEmailNotFound() {
        // Arrange
        String email = "nonexistent@example.com";
        Integer month = 4;
        Integer year = 2024;
        // Mocking
        when(traineeRepository.existsByEmail(email)).thenReturn(false);
        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                traineeService.getMonthlyReport(email, month, year));
        assertEquals("Trainee's email not found", exception.getMessage());
    }

}



































