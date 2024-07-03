package com.curso_loiane.crud_spring.model;

import com.curso_loiane.crud_spring.enums.Category;
import com.curso_loiane.crud_spring.enums.converters.CategoryConverter;
import com.curso_loiane.crud_spring.enums.Status;
import com.curso_loiane.crud_spring.enums.converters.StatusConverter;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/*@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor*  ou coloca o data/ */

@Data
@Entity
//@Table(name = "course") se o nome da tabela for diferente do nome da classe ou ja tiver uma tabela no bd
@SQLDelete(sql = "UPDATE Course SET status = 'Inativo' WHERE id = ?") //soft delete
@Where(clause = "status = 'Ativo'") //so vai trazer os ativos, em todos os where
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
    @Column(length = 10, nullable = false)
    @Convert(converter = CategoryConverter.class) //usa o converter inves de Enumerated
    private Category category;

    @NotNull
    @Column(length = 10, nullable = false)
    @Convert(converter = StatusConverter.class)
    private Status status = Status.ATIVO;
}
