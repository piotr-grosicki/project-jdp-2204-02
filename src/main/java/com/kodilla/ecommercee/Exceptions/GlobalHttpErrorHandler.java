package com.kodilla.ecommercee.Exceptions;

import com.kodilla.ecommercee.Exeptions.UserNotFoundException;
import com.kodilla.ecommercee.Exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException exception) {
        return new ResponseEntity<>("Product with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception) {
        return new ResponseEntity<>("User with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }
}