package br.com.orangex.api.exception;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String message, String id) {
        super(String.format("%s (%s) not found...", message, id));
    }

}
