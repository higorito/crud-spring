package com.curso_loiane.crud_spring.controller;

import com.curso_loiane.crud_spring.exception.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice  //fala pra todos rest controllers que se acontecer uma exceção
public class ApplicationControllerAdvice {

    @ExceptionHandler(RecordNotFoundException.class) //qual exceção ele vai tratar
    @ResponseStatus(HttpStatus.NOT_FOUND) //vai retornar 404
    public String handleNotFoundException(RecordNotFoundException ex){
        return ex.getMessage(); //é aquela q eu criei
    }
}
