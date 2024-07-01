package com.curso_loiane.crud_spring.model;

import jakarta.persistence.*;
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
    private long id;

    @Column(length = 200, nullable = false) //quanto mais especifico melhor p/ validação
    private String name;

    @Column(length = 100, nullable = false)
    private String category;
}
