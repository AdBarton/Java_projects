package Exceptions;

public class NameLengthException extends RuntimeException{
    public NameLengthException(String message){
        super(message);
    }
}
