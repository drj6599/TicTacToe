package TicTacToe.exception;

public class InvalidPlayerCount extends RuntimeException{
    public InvalidPlayerCount() {
        super();
    }

    public InvalidPlayerCount(String message) {
        super(message);
    }

    public InvalidPlayerCount(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPlayerCount(Throwable cause) {
        super(cause);
    }

    protected InvalidPlayerCount(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
