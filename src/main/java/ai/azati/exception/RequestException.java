package ai.azati.exception;

public class RequestException extends Exception {

    public RequestException(String format) {
        super(format);
    }

    public RequestException(String format, Throwable throwable) {
        super(format, throwable);
    }
}
