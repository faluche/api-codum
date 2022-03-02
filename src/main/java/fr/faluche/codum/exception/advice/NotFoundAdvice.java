package fr.faluche.codum.exception.advice;

import fr.faluche.codum.exception.MessageNotFoundException;
import fr.faluche.codum.exception.SubjectNotFoundException;
import fr.faluche.codum.exception.TopicNotFoundException;
import fr.faluche.codum.exception.CityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice(annotations = RestController.class)
public class NotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(MessageNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String messageNotFoundHandler(MessageNotFoundException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(SubjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String subjectNotFoundHandler(SubjectNotFoundException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(TopicNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String topicNotFoundHandler(TopicNotFoundException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(CityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String cityNotFoundHandler(CityNotFoundException e) {
        return e.getMessage();
    }
}
