package net.javaguides.spring_security.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.LocalDateTime;

public class CustomAuthenticationEntryPoint
        implements AuthenticationEntryPoint {
    @Override
    public void commence
            (HttpServletRequest request,
             HttpServletResponse response,
             AuthenticationException authException) throws
            IOException, ServletException {

        response.setHeader("error-reason", "Unauthorized");
        response.setContentType("application/json");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setTimeStamp(LocalDateTime.now());
        errorResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        errorResponse.setError(HttpStatus.UNAUTHORIZED.getReasonPhrase());
        errorResponse.setMessage(authException.getMessage());
        errorResponse.setPath(request.getRequestURI());



        response.getWriter()
                .write(new ObjectMapper().writeValueAsString(errorResponse));
    }
}
