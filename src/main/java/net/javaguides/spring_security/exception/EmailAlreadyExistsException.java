package net.javaguides.spring_security.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException
            (String message) {
        super(message);
    }
}
