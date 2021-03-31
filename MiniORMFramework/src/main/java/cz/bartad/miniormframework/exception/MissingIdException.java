package cz.bartad.miniormframework.exception;

public class MissingIdException extends RuntimeException{
    public MissingIdException(String message) {
        super(message);
    }
}
