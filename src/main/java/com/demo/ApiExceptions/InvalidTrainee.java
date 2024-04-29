package com.demo.ApiExceptions;

import lombok.Data;

@Data

public class InvalidTrainee extends RuntimeException {

    private String code;
    public InvalidTrainee() {
    }

    public InvalidTrainee(String code,String message) {
        super(message);
        this.code = code;
    }




}