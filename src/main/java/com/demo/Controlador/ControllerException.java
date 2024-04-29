package com.demo.Controlador;

import com.demo.ApiExceptions.BusinessException;
import com.demo.DTO.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice

public class ControllerException {

     @ExceptionHandler(value = RuntimeException.class)
     public ResponseEntity<ErrorDTO> runtimeExceptionHandler (RuntimeException ex){
         ErrorDTO error = ErrorDTO.builder().code("P-500").message(ex.getMessage()).build();
         return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
     }

     @ExceptionHandler(value = BusinessException.class)
     public ResponseEntity<ErrorDTO> businessExceptionHandler (BusinessException ex) {
         ErrorDTO error = ErrorDTO.builder().code(ex.getCode()).message(ex.getMessage()).build();
         return new ResponseEntity<>(error, ex.getStatus());
     }
}
