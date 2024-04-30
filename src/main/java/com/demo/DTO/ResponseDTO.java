package com.demo.DTO;

public class ResponseDTO <T>{
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
    public static <T>ResponseDTO<T> success(T data){
        return new ResponseDTO<>(data);
    }
    public static <T>ResponseDTO<T> error(String text){
        return new ResponseDTO<>(text);
    }
}