package five.exception;

public class CarIllegalArgumentException extends Exception {
    public CarIllegalArgumentException() {
        super();
    }

    public CarIllegalArgumentException(String msg) {
        super(msg);
    }

    public CarIllegalArgumentException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
