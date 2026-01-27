package net.javaguides.spring_security.exception;


import java.time.LocalDateTime;

public class ErrorResponse {

    private LocalDateTime timeStamp;
    private String error ;
    private String message;
    private int status ;
    private String path ;

    public ErrorResponse() {
    }

    public ErrorResponse(LocalDateTime timeStamp, String error, String message, int status, String path) {
        this.timeStamp = timeStamp;
        this.error = error;
        this.message = message;
        this.status = status;
        this.path = path;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
