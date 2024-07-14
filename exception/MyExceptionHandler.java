package application.exception;

import application.exception.type.NoContentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(MyException.class)
    public ResponseEntity<?> handleResourceNotFoundException(NoContentException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
    }
}
