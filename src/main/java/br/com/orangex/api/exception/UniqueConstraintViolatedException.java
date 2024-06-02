package br.com.orangex.api.exception;

public class UniqueConstraintViolatedException extends RuntimeException{

    public UniqueConstraintViolatedException() {
        super("Unique field value is duplicated!");
    }

}
