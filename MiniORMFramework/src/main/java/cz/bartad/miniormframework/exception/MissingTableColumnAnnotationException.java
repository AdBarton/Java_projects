package cz.bartad.miniormframework.exception;

public class MissingTableColumnAnnotationException  extends  RuntimeException{
    public MissingTableColumnAnnotationException(String message) {
        super(message);
    }
}
