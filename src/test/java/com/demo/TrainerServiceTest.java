package com.demo;

import com.demo.DTO.*;
import com.demo.Modelo.Trainer;
import com.demo.Repository.TrainerRepository;
import com.demo.Service.TrainerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TrainerServiceTest {
    private TrainerService trainerService;
    private TrainerRepository trainerRepository;

    @BeforeEach
    public void construir(){
        trainerRepository = mock(TrainerRepository.class);
        trainerService = new TrainerService(trainerRepository);
    }

    @Test
    public void addPatientWhenNameIsNull(){
        // Arrange
        Trainer trainer = new Trainer();
        trainer.setName(null);
        trainer.setEmail("null");
        trainer.setPassword("null");
        // Act and Assert
        assertThrows(RuntimeException.class, () -> this.trainerService.addTrainer(trainer));
    }
    @Test
    public void addPatientWhenEmailIsNull(){
        // Arrange
        Trainer trainer = new Trainer();
        trainer.setName("null");
        trainer.setEmail(null);
        trainer.setPassword("null");
        // Act and Assert
        assertThrows(RuntimeException.class, () -> this.trainerService.addTrainer(trainer));
    }
    @Test
    public void addPatientWhenPasswordIsNull(){
        // Arrange
        Trainer trainer = new Trainer();
        trainer.setName("null");
        trainer.setEmail("null");
        trainer.setPassword(null);
        // Act and Assert
        assertThrows(RuntimeException.class, () -> this.trainerService.addTrainer(trainer));
    }
    @Test
    void addTrainerWhenTrainerAlreadyExistsThrowsException() {
        // Arrange
        Trainer trainer = new Trainer();
        trainer.setName("John Doe");
        trainer.setEmail("john.doe@example.com");
        trainer.setPassword("password");
        // Mocking the behavior of existByEmail to return true, indicating the email already exists
        when(trainerRepository.existsByEmail(trainer.getEmail())).thenReturn(false);
        // Act and Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            trainerService.addTrainer(trainer);
        });
        assertEquals("Trainer's email: "+ trainer.getEmail()+" already registered", exception.getMessage());
    }
    @Test
    void addTrainerWithoutException() {
        // Arrange
        Trainer trainer = new Trainer();
        trainer.setName("John Doe");
        trainer.setEmail("john.doe@example.com");
        trainer.setPassword("password");
        // Mocking the behavior of existByEmail to return true, indicating the email already exists
        when(trainerRepository.existsByEmail(trainer.getEmail())).thenReturn(true);
        when(trainerRepository.save(trainer)).thenReturn(trainer);
        TrainerDTO trainerDTO = TrainerMapper.mapper.trainerToTrainerDTO(trainer);
        // Act and Assert
        assertEquals(trainerDTO,trainerService.addTrainer(trainer));
    }
    @Test
    public void updateTrainerInfoWhenEmailDoesntExists(){
        // Arrange
        Trainer trainer = new Trainer();
        trainer.setEmail("john.doe@example.com");
        Trainer updatedTrainerInfo = new Trainer();
        // Mocking
        when(trainerRepository.existsByEmail(trainer.getEmail())).thenReturn(false);
        // Act and Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            trainerService.updateTrainerInfo(trainer.getEmail(), updatedTrainerInfo);
        });
        assertEquals("The trainer's information you want to update doesn't exist in our system", exception.getMessage());
    }
    @Test
    public void updateTrainerInfoWhenNameIsNull(){
        // Arrange
        Trainer trainer = new Trainer();
        trainer.setEmail("john.doe@example.com");
        Trainer updatedTrainerInfo = new Trainer();
        updatedTrainerInfo.setName(null);
        // Mocking
        when(trainerRepository.existsByEmail(trainer.getEmail())).thenReturn(true);
        // Act and Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            trainerService.updateTrainerInfo(trainer.getEmail(), updatedTrainerInfo);
        });
        assertEquals("A name is required", exception.getMessage());
    }
    @Test
    public void updateTrainerInfoWhenEmailIsNull(){
        // Arrange
        Trainer trainer = new Trainer();
        trainer.setEmail("john.doe@example.com");
        Trainer updatedTrainerInfo = new Trainer();
        updatedTrainerInfo.setName("null");
        updatedTrainerInfo.setEmail(null);
        // Mocking
        when(trainerRepository.existsByEmail(trainer.getEmail())).thenReturn(true);
        // Act and Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            trainerService.updateTrainerInfo(trainer.getEmail(), updatedTrainerInfo);
        });
        assertEquals("Invalid email", exception.getMessage());
    }

    @Test
    public void updateTrainerInfoWhenPasswordIsNull(){
        // Arrange
        Trainer trainer = new Trainer();
        trainer.setEmail("john.doe@example.com");
        Trainer updatedTrainerInfo = new Trainer();
        updatedTrainerInfo.setName("null");
        updatedTrainerInfo.setEmail("null");
        updatedTrainerInfo.setPassword(null);
        // Mocking
        when(trainerRepository.existsByEmail(trainer.getEmail())).thenReturn(true);
        // Act and Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            trainerService.updateTrainerInfo(trainer.getEmail(), updatedTrainerInfo);
        });
        assertEquals("Invalid password", exception.getMessage());
    }
    @Test
    void updateTrainerInfoTrainerExistsSaveSuccessfully() {
        // Arrange
        String email = "jane.smith@example.com";
        Trainer updatedTrainerInfo = new Trainer();
        updatedTrainerInfo.setName("Jane Smith");
        updatedTrainerInfo.setEmail(email);
        updatedTrainerInfo.setPassword("newPassword");
        updatedTrainerInfo.setSpeciality("Weightlifting");
        updatedTrainerInfo.setExperience(5);
        updatedTrainerInfo.setCertifications("ACE Certified");
        Trainer existingTrainer = new Trainer();
        existingTrainer.setEmail(email);
        // Mocking the behavior of existByEmail to return true, indicating the trainer exists
        when(trainerRepository.existsByEmail(email)).thenReturn(true);
        when(trainerRepository.findByEmail(email)).thenReturn(existingTrainer);
        // Act
        TrainerDTO result = trainerService.updateTrainerInfo(email, updatedTrainerInfo);
        // Assert
        assertNotNull(result);
        assertEquals(updatedTrainerInfo.getName(), existingTrainer.getName());
        assertEquals(updatedTrainerInfo.getEmail(), existingTrainer.getEmail());
        assertEquals(updatedTrainerInfo.getSpeciality(), existingTrainer.getSpeciality());
        assertEquals(updatedTrainerInfo.getExperience(), existingTrainer.getExperience());
        assertEquals(updatedTrainerInfo.getCertifications(), existingTrainer.getCertifications());
    }
    @Test
    public void showTrainerInfoWhenEmailNotFound(){
        // Arrange
        Trainer trainer = new Trainer();
        trainer.setEmail("john.doe@example.com");
        // Mocking
        when(trainerRepository.existsByEmail(trainer.getEmail())).thenReturn(false);
        // Act and Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            trainerService.showTrainerInfo(trainer.getEmail());
        });
        assertEquals("Trainer's email not found", exception.getMessage());
    }

    @Test
    public void showTrainerInfoWithNoException(){
        // Arrange
        Trainer trainer = new Trainer();
        trainer.setName("John Doe");
        trainer.setEmail("john.doe@example.com");
        trainer.setPassword("password");
        // Mocking
        when(trainerRepository.existsByEmail(trainer.getEmail())).thenReturn(true);
        when(trainerRepository.findByEmail(trainer.getEmail())).thenReturn(trainer);
        TrainerDTO trainerDTO = TrainerMapper.mapper.trainerToTrainerDTO(trainer);
        // Act and Assert
        assertEquals(trainerDTO, trainerService.showTrainerInfo(trainer.getEmail()));
    }
    @Test
    public void changeTrainerPasswordWhenEmailDoesntExist(){
        // Arrange
        RequestTrainerDTO trainer = new RequestTrainerDTO();
        trainer.setEmail("john.doe@example.com");
        // Mocking
        when(trainerRepository.existsByEmail(trainer.getEmail())).thenReturn(false);
        // Act and Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            trainerService.changeTrainerPassword(trainer);
        });
        assertEquals("Trainer's email not found", exception.getMessage());
    }

    @Test
    public void changeTrainerPasswordWhenPasswordIsNull(){
        RequestTrainerDTO trainer = new RequestTrainerDTO();
        trainer.setEmail("john.doe@example.com");
        trainer.setName("John Doe");
        trainer.setPassword(null);
        // Mocking
        when(trainerRepository.existsByEmail(trainer.getEmail())).thenReturn(true);
        when(trainerRepository.findByEmail(anyString())).thenReturn(any());
        // Act and Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            trainerService.changeTrainerPassword(trainer);
        });
        assertEquals("New password is empty or same as the old password", exception.getMessage());
    }

    @Test
    public void changeTrainerPassordWithNoExeption(){
        RequestTrainerDTO requestTrainerDTO = new RequestTrainerDTO();
        requestTrainerDTO.setEmail("john.doe@example.com");
        requestTrainerDTO.setName("John Doe");
        requestTrainerDTO.setPassword("null");
        Trainer trainer = new Trainer();
        trainer.setName("John Doe");
        trainer.setEmail("john.doe@example.com");
        trainer.setPassword("password");
        // Mocking
        when(trainerRepository.existsByEmail(requestTrainerDTO.getEmail())).thenReturn(true);
        when(trainerRepository.findByEmail(requestTrainerDTO.getEmail())).thenReturn(trainer);
        // Act and Assert
        assertTrue(() -> trainerService.changeTrainerPassword(requestTrainerDTO));
    }
}