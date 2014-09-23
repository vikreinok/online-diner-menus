/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.rest.exception;

import com.example.rest.response.ApiResponse;
import com.example.rest.response.ResponseFactory;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.map.exc.UnrecognizedPropertyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author craigbrookes
 */
@ControllerAdvice
public class ApiControllerExceptionHandler {
    
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)        
    ApiResponse handleException(UnrecognizedPropertyException ex){
        return ResponseFactory.get(HttpStatus.BAD_REQUEST, "invalid params sent");
    }
    
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiResponse handleException(HttpMessageNotReadableException e){
         return ResponseFactory.get(HttpStatus.BAD_REQUEST, "invalid params sent");
    }
    
    
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiResponse handleException(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<ObjectError> globalErrors = ex.getBindingResult().getGlobalErrors();
        List<String> errors = new ArrayList<String>(fieldErrors.size() + globalErrors.size());
        String error;
        for (FieldError fieldError : fieldErrors) {
            error = fieldError.getField() + ", " + fieldError.getDefaultMessage();
            errors.add(error);
        }
        for (ObjectError objectError : globalErrors) {
            error = objectError.getObjectName() + ", " + objectError.getDefaultMessage();
            errors.add(error);
        }
        return ResponseFactory.get(HttpStatus.BAD_REQUEST, errors);
    }
    
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ApiResponse handleException(Exception e){
        System.out.println("handling exception " + e.getMessage() + e.getClass().getName());
        //e.printStackTrace();
        return ResponseFactory.get(HttpStatus.INTERNAL_SERVER_ERROR);
        
    }
    
}
