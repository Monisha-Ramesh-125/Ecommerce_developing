package com.example.Ecommerce.Exception;

public class ResourceAlreadyExistsException extends RuntimeException {
     public ResourceAlreadyExistsException(String message){
         super(message);
     }
}
