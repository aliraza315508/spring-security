package net.javaguides.spring_security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorDetails>
    handleUsernameNotFoundException(UsernameNotFoundException exception,
    WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails() ;
        errorDetails.setTimestamp(LocalDateTime.now());
        errorDetails.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        errorDetails.setStatus(HttpStatus.BAD_REQUEST.value());
        errorDetails.setPath(webRequest.getDescription(false));
        errorDetails.setMessage(exception.getMessage());

        return new ResponseEntity<>(errorDetails , HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorDetails>
    handleEmailAlreadyExistsException(EmailAlreadyExistsException exception,
                                    WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails() ;
        errorDetails.setTimestamp(LocalDateTime.now());
        errorDetails.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        errorDetails.setStatus(HttpStatus.BAD_REQUEST.value());
        errorDetails.setPath(webRequest.getDescription(false));
        errorDetails.setMessage(exception.getMessage());

        return new ResponseEntity<>(errorDetails , HttpStatus.BAD_REQUEST);

    }
}
