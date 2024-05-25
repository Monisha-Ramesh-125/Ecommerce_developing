package com.example.Ecommerce.Handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {

    public static ResponseEntity<Object> GenerateResponse(String msg, HttpStatus httpStatus){
        Map<String,Object> map = new HashMap<>();
        map.put("msg",msg);
        map.put("status",httpStatus);

        return new ResponseEntity<>(map,httpStatus);
    }
    public static ResponseEntity<Object> GenerateResponse(Object obj,String msg, HttpStatus status){

        Map<String,Object> map = new HashMap<>();
        map.put("msg",msg);
        map.put("status",status);
        map.put("data",obj);
        return new ResponseEntity<>(map,status);
    }
}
