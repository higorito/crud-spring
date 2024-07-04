package com.curso_loiane.crud_spring.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 111, nullable = false)
    private String youtubeUrl;

    @ManyToOne(fetch = FetchType.LAZY, optional = false) //muitas aulas para um curso, o lazy é para mapear so qnd chamar o getCurso
        //optional fala que a coluna de course_id nao pode ser nula
    @JoinColumn(name = "course_id", nullable = false) //p dar o nome
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //para nao causar loop infinito
    private Course course;
}
