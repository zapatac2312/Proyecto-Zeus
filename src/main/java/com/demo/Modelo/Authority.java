package com.demo.Modelo;

import com.demo.Util.AuthorityName;
import jakarta.persistence.*;

@Entity(name = "authorities")
public class Authority {

    //Roles
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;


    @Enumerated(EnumType.STRING)
    private AuthorityName name;



    public Authority(AuthorityName authorityName){
        this.name= authorityName;
    }

    public Authority(){
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public AuthorityName getName() {
        return name;
    }

    public void setName(AuthorityName name) {
        this.name = name;
    }
}