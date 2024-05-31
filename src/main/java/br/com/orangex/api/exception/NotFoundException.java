package br.com.orangex.api.exception;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String message, Long id) {
        super(String.format("%s (id: %d) not found...", message, id));
    }

}
