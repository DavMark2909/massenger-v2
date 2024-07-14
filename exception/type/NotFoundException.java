package application.exception.type;

import application.exception.MyException;
import org.springframework.http.HttpStatus;

public class NotFoundException extends MyException{

    public NotFoundException(String name) {
        super(HttpStatus.NOT_FOUND, name);
    }
}
