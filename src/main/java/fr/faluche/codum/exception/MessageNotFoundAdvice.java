package fr.faluche.codum.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class MessageNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(MessageNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String messageNotFoundHandler(MessageNotFoundException e) {
        return e.getMessage();
    }
}
