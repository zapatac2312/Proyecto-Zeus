package com.demo.DTO;

public class ResponseDTO <T> {

    private String message;
    private T data;

    public ResponseDTO(T data) {
        this.data = data;
    }

    public ResponseDTO(String message) {
        this.message = message;
    }
    public ResponseDTO() {
    }
}
