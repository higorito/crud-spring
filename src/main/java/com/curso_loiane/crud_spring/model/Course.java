package com.curso_loiane.crud_spring.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

/*@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor*  ou coloca o data/ */

@Data
@Entity
//@Table(name = "course") se o nome da tabela for diferente do nome da classe ou ja tiver uma tabela no bd
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private long id;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 60)
    @Column(length = 60, nullable = false) //quanto mais especifico melhor p/ validação
    private String name;

    @NotNull
    @Size(max = 30)
    @Pattern(regexp = "Backend|Frontend|Mobile")
    @Column(length = 30, nullable = false)
    private String category;
}
