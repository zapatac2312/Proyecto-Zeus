package com.demo.Modelo;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.TypeAlias;

import java.util.ArrayList;
import java.util.List;


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

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public String getCertifications() {
        return certifications;
    }

    public void setCertifications(String certifications) {
        this.certifications = certifications;
    }

    public List<Trainee> getTrainee() {
        return trainee;
    }

    public void setTrainee(List<Trainee> trainee) {
        this.trainee = trainee;
    }
}
