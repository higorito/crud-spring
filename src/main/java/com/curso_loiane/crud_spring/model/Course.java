package com.curso_loiane.crud_spring.model;

import com.curso_loiane.crud_spring.enums.Category;
import com.curso_loiane.crud_spring.enums.converters.CategoryConverter;
import com.curso_loiane.crud_spring.enums.Status;
import com.curso_loiane.crud_spring.enums.converters.StatusConverter;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

/*@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor*  ou coloca o data/ */


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
    @Length(min = 3, max = 60)
    @Column(length = 60, nullable = false)
    private String name;

    @NotNull
    @Column(length = 10, nullable = false)
    @Convert(converter = CategoryConverter.class) //usa o converter inves de Enumerated
    private Category category;

    @NotNull
    @Column(length = 10, nullable = false)
    @Convert(converter = StatusConverter.class)
    private Status status = Status.ATIVO;


    @NotNull
    @NotEmpty
    @Valid
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "course") //se deletar o curso, deleta as aulas
    private List<Lesson> lessons = new ArrayList<>();
    //o jois colmun cria um fluxo uni-direcional,dai vai dar problema de performance ai tem q usar o mappedBy aqui e la na outra classe
    //@JoinColumn(name = "course_id") //nome da coluna na tabela lesson, senao cria uma tabela intermediaria(estilo many to many)
    //ai cria essa coluna na tabela lesson


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public @NotNull @NotBlank @Length(min = 3, max = 60) String getName() {
        return name;
    }

    public void setName(@NotNull @NotBlank @Length(min = 3, max = 60) String name) {
        this.name = name;
    }

    public @NotNull Category getCategory() {
        return category;
    }

    public void setCategory(@NotNull Category category) {
        this.category = category;
    }

    public @NotNull Status getStatus() {
        return status;
    }

    public void setStatus(@NotNull Status status) {
        this.status = status;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}
