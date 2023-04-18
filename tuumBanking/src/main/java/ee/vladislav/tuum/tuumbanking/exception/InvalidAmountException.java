package ee.vladislav.tuum.tuumbanking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class InvalidAmountException extends IllegalArgumentException {
    public InvalidAmountException(String message) {
    }
}
