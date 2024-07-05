package com.curso_loiane.crud_spring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record LessonDTO(
        Long id,
        @NotNull @NotBlank @Length(min = 3, max = 60) String name,
        @NotNull @NotBlank @Length(min = 10, max = 20) String youtubeUrl
        ) {
}
