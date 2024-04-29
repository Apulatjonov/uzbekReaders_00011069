package com.example.bookstore.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by Abdulaziz Pulatjonov
 * Date: 03/11/2024 14:18
 */
@ControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = UserAlreadyExistException.class)
    protected ResponseEntity<?> handleUserAlreadyExistException(
            RuntimeException ex, WebRequest request){
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
    @ExceptionHandler(value = InvalidEmailAddressException.class)
    protected ResponseEntity<?> handleInvalidEmailAddressException(
            RuntimeException ex, WebRequest request){
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
    @ExceptionHandler(value = PasswordIncorrectException.class)
    protected ResponseEntity<?> handlePasswordIncorrectException(
            RuntimeException ex, WebRequest request){
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.valueOf(401), request);
    }
    @ExceptionHandler(value = UserNotFoundException.class)
    protected ResponseEntity<?> handleUserNotFoundException(
            RuntimeException ex, WebRequest request){
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
    @ExceptionHandler(value = CardAlreadyExistException.class)
    protected ResponseEntity<?> handleCardAlreadyExistException(
            RuntimeException ex, WebRequest request){
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
    @ExceptionHandler(value = IncorrectCardDetailsException.class)
    protected ResponseEntity<?> handleIncorrectCardDetailsException(
            RuntimeException ex, WebRequest request){
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
    @ExceptionHandler(value = CardNotFoundException.class)
    protected ResponseEntity<?> handleCardNotFoundException(
            RuntimeException ex, WebRequest request){
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
    @ExceptionHandler(value = InsufficientCardBalanceException.class)
    protected ResponseEntity<?> handleInsufficientCardBalanceException(
            RuntimeException ex, WebRequest request){
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
    @ExceptionHandler(value = NotFoundException.class)
    protected ResponseEntity<?> handleNotFoundException(
            RuntimeException ex, WebRequest request){
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = AlreadyRatedException.class)
    protected ResponseEntity<?> handleAlreadyRatedException(
            AlreadyRatedException ex, WebRequest request){
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.valueOf(402), request);
    }
    @ExceptionHandler(value = NotEnoughFundingException.class)
    protected ResponseEntity<?> handleNotEnoughFundingException(
            NotEnoughFundingException ex, WebRequest request){
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.valueOf(402), request);
    }
}
