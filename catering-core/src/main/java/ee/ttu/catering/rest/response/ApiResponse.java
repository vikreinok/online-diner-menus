/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.ttu.catering.rest.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ApiResponse implements Serializable{
    
    private HttpStatus status;
    
    private String message;
    
    private List result = new ArrayList();

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List getResult() {
        return result;
    }

    public void setResult(List result) {
        this.result = result;
    }

    public ApiResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
    
    public ApiResponse(HttpStatus status) {
        this.status = status;
        this.message = "";
    }
    
    public ApiResponse(HttpStatus status, List result) {
        this.status = status;
        this.message = "";
    }
    
    public ApiResponse(HttpStatus status, String message, List result) {
        this.status = status;
        this.message = message;
        this.result = result;
                
    }
    
    public ApiResponse(HttpStatus status, String message, Serializable singleResult) {
        this.status = status;
        this.message = message;
        this.result.add(singleResult);
    }
    
    public ApiResponse(HttpStatus status, Serializable singleResult) {
        this.status = status;
        this.message = "";
        this.result.add(singleResult);
    }
    
    
    
}
