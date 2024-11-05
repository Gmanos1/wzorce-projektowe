package km.Projekt.exception;

//L3 - klasa do rzucania wyjątków niepoprawnego ID
public class InvalidIDException extends RuntimeException {
    public InvalidIDException(String message) {
        super(message);
    }
}
