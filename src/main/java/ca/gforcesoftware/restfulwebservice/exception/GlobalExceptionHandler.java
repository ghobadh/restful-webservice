package ca.gforcesoftware.restfulwebservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

/**
 * @author gavinhashemi on 2024-11-11
 */
//When we want to use Exception handling in globally, we need to add @ControllerAdvice Annotation. Note
// that @ExceptionHandler([ResourceName].class) will handle only the ResourceName specifics exception
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourcNotFoundExcpetion(UserNotFoundException exception,
                                                                       WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                /* I put the getDescription as false, otherwise it will how the client information like this
                "path": "uri=/api/users/delete/9;client=127.0.0.1",
                 */
                webRequest.getDescription(false),
                "USER_NOT_FOUND"
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorDetails> handleAlreadyEmailIsAlreadyExist (EmailAlreadyExistsException exception,
                                                                       WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                /* I put the getDescription as false, otherwise it will how the client information like this
                "path": "uri=/api/users/delete/9;client=127.0.0.1",
                 */
                webRequest.getDescription(false),
                "USER_EMAIL_ALREADY_EXISTS"
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }
}
