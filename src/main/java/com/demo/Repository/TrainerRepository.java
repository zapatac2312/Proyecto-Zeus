package com.demo.Repository;

import com.demo.DTO.TrainerDTO;
import com.demo.Modelo.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    Optional<Trainer> findByEmailAndPassword(String email, String password);
}
