package com.curso_loiane.crud_spring.controller;

import com.curso_loiane.crud_spring.exception.RecordNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice  //fala pra todos rest controllers que se acontecer uma exceção
public class ApplicationControllerAdvice {

    @ExceptionHandler(RecordNotFoundException.class) //qual exceção ele vai tratar
    @ResponseStatus(HttpStatus.NOT_FOUND) //vai retornar 404
    public String handleNotFoundException(RecordNotFoundException ex){
        return ex.getMessage(); //é aquela q eu criei
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        return ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .reduce("", (s1, s2) -> s1 + "\n" + s2);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleConstraintViolationException(ConstraintViolationException ex){
        return ex.getConstraintViolations().stream()
                .map(cv -> cv.getPropertyPath() + ": " + cv.getMessage())
                .reduce("", (s1, s2) -> s1 + "\n" + s2);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex){

        return ex.getName() + " deve ser do tipo " + ex.getRequiredType().getName().split("\\.")[2];
    }

}
