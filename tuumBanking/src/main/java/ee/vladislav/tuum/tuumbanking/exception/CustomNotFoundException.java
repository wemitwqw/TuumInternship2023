package ee.vladislav.tuum.tuumbanking.exception;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CustomNotFoundException extends NotFoundException {

    public CustomNotFoundException(String message) {
        super(message);
    }

}