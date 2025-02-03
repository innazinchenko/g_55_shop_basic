package app.exceptions;

public class ProductNotFounException extends RuntimeException {
    public ProductNotFounException(String message) {
        super(message);
    }
}
