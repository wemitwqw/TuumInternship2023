package ee.vladislav.tuum.tuumbanking.exception;

public class InsufficientFundsException extends RuntimeException  {
    public InsufficientFundsException(String msg ) {
        super(msg);
    }
}
