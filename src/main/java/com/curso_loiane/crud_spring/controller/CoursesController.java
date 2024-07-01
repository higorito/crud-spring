package com.curso_loiane.crud_spring.controller;

import com.curso_loiane.crud_spring.model.Course;
import com.curso_loiane.crud_spring.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
public class CoursesController {

    //@Autowired pode ser assim, mas quando for obrigatorio, é melhor colocar no construtor, ai faz manual ou via lombok
    private CourseRepository courseRepository;

    /*public CoursesController(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }/*/


    @GetMapping
    public List<Course> list(){
        return courseRepository.findAll();
    }
}
