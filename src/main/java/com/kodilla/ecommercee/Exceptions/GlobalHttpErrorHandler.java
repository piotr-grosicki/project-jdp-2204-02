package com.kodilla.ecommercee.Exceptions;

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

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Object> handleOrderNotFoundException(OrderNotFoundException exception) {
        return new ResponseEntity<>("Order with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<Object> handleCartNotFoundException(CartNotFoundException exception) {
        return new ResponseEntity<>("Cart with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GroupNotFoundException.class)
    public ResponseEntity<Object> handleGroupNotFoundException(GroupNotFoundException exception) {
        return new ResponseEntity<>("Group with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InsufficientPermissionsException.class)
    public ResponseEntity<Object> handleInsufficientPermissionsException(InsufficientPermissionsException exception){
        return new ResponseEntity<>("You don't have permission to access this data",HttpStatus.BAD_REQUEST);
    }
}
