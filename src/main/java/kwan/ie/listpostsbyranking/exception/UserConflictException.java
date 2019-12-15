package kwan.ie.listpostsbyranking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserConflictException extends RuntimeException{
    public UserConflictException(String message) {
        super(message);
    }
}
