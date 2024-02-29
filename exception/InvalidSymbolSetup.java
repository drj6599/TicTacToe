package TicTacToe.exception;

public class InvalidSymbolSetup extends RuntimeException{
    public InvalidSymbolSetup() {
        super();
    }

    public InvalidSymbolSetup(String message) {
        super(message);
    }

    public InvalidSymbolSetup(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidSymbolSetup(Throwable cause) {
        super(cause);
    }

    protected InvalidSymbolSetup(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
