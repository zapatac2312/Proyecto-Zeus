package com.demo.ApiExceptions;

import lombok.Data;

@Data

public class InvalidTrainer extends RuntimeException{

    private String code;

    public InvalidTrainer() {
    }


    public InvalidTrainer(String code, String message) {
        super(message);
        this.code = code;
    }


}

