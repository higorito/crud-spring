package com.curso_loiane.crud_spring.controller;

import com.curso_loiane.crud_spring.dto.CourseDTO;

import com.curso_loiane.crud_spring.dto.CoursePageDTO;
import com.curso_loiane.crud_spring.service.CourseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
//@AllArgsConstructor
@Validated //pro controller aceitar validações, do not null, positive. se nao tiver o @Valid ja da conta
public class CoursesController {

    private final CourseService courseService;

    public CoursesController(CourseService courseService) {
        this.courseService = courseService;
    }

    /*@GetMapping
    public List<CourseDTO> list(){
        return courseService.list();
    }*/

    @GetMapping
    public CoursePageDTO list(@RequestParam @PositiveOrZero int page,
                              @RequestParam @Positive @Max(100) int size){  //validacao mt importante para evitar erros// alguem pode ir la e colocar 10k
        return courseService.list(page, size);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CourseDTO create(@RequestBody @Valid @NotNull CourseDTO course){
        return courseService.create(course);
    }

    @GetMapping("/{id}")
    public CourseDTO findById(@PathVariable @NotNull @Positive Long id){
        return courseService.findById(id);
    }

    @PutMapping("/{id}")
    public CourseDTO update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid @NotNull CourseDTO course){
        return courseService.update(id, course);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT) //pq ai "sobrescreve" o status 200 "ok" assim que deletar
    public void delete(@PathVariable @NotNull @Positive Long id){
        courseService.delete(id);
    }

}
