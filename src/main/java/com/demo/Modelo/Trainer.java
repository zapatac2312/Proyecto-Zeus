package com.demo.Modelo;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.TypeAlias;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "trainer")
public class Trainer {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "speciality")
    private String speciality;
    @Column(name = "experience")
    private Integer experience;
    @Column(name = "certifications")
    private String certifications;

    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private List<Trainee> trainee;

    public void addTrainee(Trainee trainee) {
        if (this.trainee == null) {
            this.trainee = new ArrayList<>();
        }
        this.trainee.add(trainee);
    }

    public Integer getTrainerAvailability(){
        return this.trainee.size();
    }

}
