package ee.vladislav.tuum.tuumbanking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class AccountHasNoSuchCurrencyException extends IllegalArgumentException{
    public AccountHasNoSuchCurrencyException(String message) {
        super(message);
    }
}