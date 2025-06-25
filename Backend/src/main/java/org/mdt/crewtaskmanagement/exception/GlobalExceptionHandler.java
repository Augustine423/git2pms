package org.mdt.crewtaskmanagement.exception;

import org.mdt.crewtaskmanagement.exception.jwt.JwtTokenExpiredException;
import org.mdt.crewtaskmanagement.exception.jwt.JwtTokenInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestControllerAdvice
public class GlobalExceptionHandler extends Exception {

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorPayload> serviceExceptionHandler(InputValidationException exception) {
        return handle(ErrorPayload.error("Invalid Input", exception.getMessages()),HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(ServiceBaseException.class)
    public ResponseEntity<ErrorPayload> serviceExceptionHandler(ServiceBaseException exception) {
        return handle(ErrorPayload.error("User Id not found Input", exception.getMessages()),HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler
    public  ResponseEntity<ErrorPayload> handleAccessDeniedException(AccessDeniedException e) {
        return handle(ErrorPayload.error("Access Denied", List.of("You have no permission to perform this operation.")), HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler
     public ResponseEntity<ErrorPayload> nullPointerException(NullPointerException e) {
        String message = Optional.ofNullable(e.getMessage()).orElse("No message provided");
        String localizedMessage = Optional.ofNullable(e.getLocalizedMessage()).orElse("No localized message");

        // Get the first element from the stack trace (where the exception was thrown)
        StackTraceElement[] stackTrace = e.getStackTrace();
        StackTraceElement origin = Arrays.stream(stackTrace)
                .filter(ste -> ste.getClassName().startsWith("org.mdt.crewtaskmanagement")) // <-- replace with your package
                .findFirst()
                .orElse(stackTrace[0]); // fallback to first if none match

        String location = "Exception in " +
                origin.getClassName() + "." +
                origin.getMethodName() + "() " +
                "at " + origin.getFileName() + ":" + origin.getLineNumber();
        return handle(ErrorPayload.error("FOUND NULL VALUE", List.of(message,localizedMessage,location)), HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler
     public ResponseEntity<ErrorPayload> handleAuthenticationException(AuthenticationException e) {
        var error = switch (e) {
            case UsernameNotFoundException ex -> ErrorPayload.error("Email Not Registered", List.of("No user found with this email: %s.".formatted(ex.getMessage())));
            case BadCredentialsException ex -> ErrorPayload.error("Wrong Password", List.of("Password is wrong. Please type valid password."));
            case JwtTokenExpiredException ex -> ErrorPayload.error("Session Expired", List.of("Your session is timed out. Please refresh or sign in to continue."));
            case JwtTokenInvalidException ex -> ErrorPayload.error("Session Invalid", List.of("Your session is invalid. Please sign in to continue."));
            default -> ErrorPayload.error("Authentication Error", List.of(e.getMessage()));
        };
        return handle(error, HttpStatus.UNAUTHORIZED);
    }


//reusable methods
    private ResponseEntity<ErrorPayload> handle(ErrorPayload error, HttpStatus status) {
        return ResponseEntity.status(status).body(error);
    }


}
