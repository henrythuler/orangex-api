package br.com.orangex.api.exception;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String message, String id) {
        super(String.format("%s (id: %s) not found...", message, id));
    }

}
