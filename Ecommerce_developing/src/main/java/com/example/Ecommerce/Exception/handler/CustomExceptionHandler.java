package com.example.Ecommerce.Exception.handler;


import com.example.Ecommerce.Exception.ResourceNotFoundException;
import com.example.Ecommerce.Exception.ResourceAlreadyExistsException;
import com.example.Ecommerce.Exception.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {


    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> CategoryAlreadyExistsHandler(ResourceAlreadyExistsException exp){
        ErrorResponse employeeErrorResponse = new ErrorResponse();
        employeeErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        employeeErrorResponse.setMessage(exp.getMessage());
        employeeErrorResponse.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(employeeErrorResponse,HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> CategoryNotFoundException(ResourceNotFoundException exp){
        ErrorResponse employeeErrorResponse = new ErrorResponse();
        employeeErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        employeeErrorResponse.setMessage(exp.getMessage());
        employeeErrorResponse.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(employeeErrorResponse,HttpStatus.NOT_FOUND);
    }




}
