package ca.gforcesoftware.restfulwebservice.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gavinhashemi on 2024-11-11
 */
//When we want to use Exception handling in globally, we need to add @ControllerAdvice Annotation. Note
// that @ExceptionHandler([ResourceName].class) will handle only the ResourceName specifics exception
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

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

    /*
    Since I used excpetion class, all errors will invoke this method.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalExceptions (Exception exception,
                                                                          WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                /* I put the getDescription as false, otherwise it will how the client information like this
                "path": "uri=/api/users/delete/9;client=127.0.0.1",
                 */
                webRequest.getDescription(false),
                "INTERNAL_SERVER_ERROR"
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
    }

    //This method is for the extension of ResponseEntityExceptionHandler
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {

        Map<String, String> errors = new HashMap<>();
        List<ObjectError> errorList = ex.getBindingResult().getAllErrors();


        errorList.forEach( error -> {
            String errorMessage = error.getDefaultMessage();
            String fieldName = ((FieldError) error).getField();
            errors.put(fieldName, errorMessage);
        });

        //instead of calling super, I use ResponseEntity and for all failure validation , I need to use BAD_REQUEST STATUS
        return new ResponseEntity<>(errorList, HttpStatus.BAD_REQUEST);
    }
}
