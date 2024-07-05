package com.curso_loiane.crud_spring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;


public class CourseDTOsemrecord {

    //ai nesse caso aqui antes do java 14 que nao tem record
    //ai eu teria que refatorar o codigo para aceitar a classe CourseDTOsemrecord

    private long _id;

    @NotNull
    @NotBlank
    @Length(min = 3, max = 60)
    private String name;

    @NotNull
    @Size(max = 30)
    @Pattern(regexp = "Backend|Frontend|Mobile")
    private String category;

    @NotNull
    @Length(max = 10)
    @Pattern(regexp = "Ativo|Inativo")
    private String status = "Ativo";
}
