package br.com.orangex.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;

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

}
