package com.curso_loiane.crud_spring.dto;

import com.curso_loiane.crud_spring.model.Lesson;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.util.List;

//nao consegue usar o record com o jpa, pq nao é considerado uma entidade
//contrutor completo
//getters e setters
//----------------------------------
//as vezes pode usar 2 dto, um para entrada e outro para saida
public record CourseDTO(
        @JsonProperty("_id") Long id,
        @NotNull @NotBlank @Length(min = 3, max = 60) String name,
        @NotNull @Length(max = 10) @Pattern(regexp = "Backend|Frontend|Mobile") String category,
        //@NotNull @Length(max = 10) @Pattern(regexp = "Ativo|Inativo") String status) pq aqui nao precisa *expor*
        List<Lesson> lessons
        )   { }
