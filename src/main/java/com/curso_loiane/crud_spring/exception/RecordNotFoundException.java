package com.curso_loiane.crud_spring.exception;

public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException(Long id) {
        super("Registro nao encontrado com o id: " + id + "!");
    }
}
