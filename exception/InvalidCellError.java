package TicTacToe.exception;

public class InvalidCellError extends RuntimeException{
    public InvalidCellError() {
    }

    public InvalidCellError(String message) {
        super(message);
    }

    public InvalidCellError(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCellError(Throwable cause) {
        super(cause);
    }

    public InvalidCellError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
