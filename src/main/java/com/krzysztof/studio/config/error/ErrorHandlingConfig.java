package com.krzysztof.studio.config.error;

import com.krzysztof.studio.config.error.model.ResourceAlreadyExistsException;
import com.krzysztof.studio.config.error.model.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandlingConfig {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity handleNotFoundException(ResourceNotFoundException exception) {
        return new ResponseEntity (exception.getDescription(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ResourceAlreadyExistsException.class)
    public ResponseEntity handleResourceAlreadyExistsException(ResourceAlreadyExistsException exception) {
        return new ResponseEntity (exception.getDescription(), HttpStatus.BAD_REQUEST);
    }
}
