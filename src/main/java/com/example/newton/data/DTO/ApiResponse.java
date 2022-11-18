package com.example.newton.data.DTO;

import org.springframework.http.HttpStatus;

public class ApiResponse {

    private HttpStatus status;
    private String debugMessage;

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }

}
