
package com.demo.Repository;


import com.demo.Modelo.ActivityReports;
import com.demo.Modelo.Trainee;
import com.demo.Modelo.Trainer;
import org.apache.catalina.User;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TraineeRepository extends JpaRepository<Trainee, Long> {
    Trainee findByEmail(String email);
    Boolean existsByEmail(String email);
    Boolean existsByName(String name);



}




