package application.exception.type;

import application.exception.MyException;
import org.springframework.http.HttpStatus;

public class NoContentException extends MyException{

    public NoContentException(String message) {
        super(HttpStatus.NO_CONTENT, message);
    }
}
