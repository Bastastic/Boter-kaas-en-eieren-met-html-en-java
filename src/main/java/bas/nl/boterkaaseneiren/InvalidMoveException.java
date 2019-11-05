package bas.nl.boterkaaseneiren;

public class InvalidMoveException extends Exception {

    public InvalidMoveException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String getMessage() {
        return "this is an invalid move jow";
    }
}
