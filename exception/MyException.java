package application.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MyException extends Exception{

    private HttpStatus status;

    public MyException(HttpStatus status, String name){
        super(name);
        this.status = status;
    }
}
