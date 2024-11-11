package ca.gforcesoftware.restfulwebservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author gavinhashemi on 2024-11-11
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmailAlreadyExistsException extends RuntimeException {
    private String message;

    public EmailAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
}
