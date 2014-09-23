/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.rest.response;

import java.io.Serializable;
import java.util.List;
import org.springframework.http.HttpStatus;

/**
 *
 * @author craigbrookes
 */
public class ResponseFactory {
    
    
    
    public static ApiResponse get(HttpStatus status){
        return new ApiResponse(status);
    }
    
    public static ApiResponse get(HttpStatus status, String message){
        return new ApiResponse(status, message);
    }
    
    public static ApiResponse get(HttpStatus status, String message, List<Serializable> results){
        return new ApiResponse(status, message, results);
    }
    
    public static ApiResponse get(HttpStatus status, String message, Serializable singleResult){
        return new ApiResponse(status, message, singleResult);
    }
    
    public static ApiResponse get(HttpStatus status, Serializable singleResult){
        return new ApiResponse(status, singleResult);
    }
    
    public static ApiResponse get(HttpStatus status, List results){
        return new ApiResponse(status, results);
    }
    
    
}
