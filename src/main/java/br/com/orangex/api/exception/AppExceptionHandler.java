package br.com.orangex.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AppExceptionHandler {

    //Handling a not found exception
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardException> handleNotFoundException(NotFoundException e, WebRequest req){
        String error = "Not found exception";
        String message = e.getMessage();
        HttpStatus status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(new StandardException(Instant.now(), status.value(), error, message, req.getDescription(false)));
    }

    //Handling an unique constraint violated exception
    @ExceptionHandler(UniqueConstraintViolatedException.class)
    public ResponseEntity<StandardException> handleUniqueConstraintViolatedException(UniqueConstraintViolatedException e, WebRequest req){
        String error = "Unique constraint violated";
        String message = e.getMessage();
        HttpStatus status = HttpStatus.CONFLICT;
        return ResponseEntity.status(status).body(new StandardException(Instant.now(), status.value(), error, message, req.getDescription(false)));
    }

    //Handling an authentication exception
    @ExceptionHandler(AuthException.class)
    public ResponseEntity<StandardException> handleAuthException(AuthException e, WebRequest req){
        String error = "Authentication exception";
        String message = e.getMessage();
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        return ResponseEntity.status(status).body(new StandardException(Instant.now(), status.value(), error, message, req.getDescription(false)));
    }

    //Handling an argument not valid exception
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        Map<String, String> errors = new HashMap<>();
        e.getFieldErrors().forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    //Handling a bad credentials exception
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<StandardException> handleBadCredentialsException(BadCredentialsException e, WebRequest req){
        String error = "Bad credentials";
        String message = "Invalid email or password";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(new StandardException(Instant.now(), status.value(), error, message, req.getDescription(false)));
    }

}
