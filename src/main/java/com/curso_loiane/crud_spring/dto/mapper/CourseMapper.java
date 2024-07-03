package com.curso_loiane.crud_spring.dto.mapper;

import com.curso_loiane.crud_spring.dto.CourseDTO;
import com.curso_loiane.crud_spring.model.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public CourseDTO toDTO(Course course){
        if (course == null) return null; //se for nulo, retorna nulo (pode ser que nao encontre o id... e ai da p famoso NULL POINTER EXCEPTION)

        return new CourseDTO(course.getId(), course.getName(), course.getCategory());
    }

    public Course toEntity(CourseDTO courseDTO){
        if (courseDTO == null) return null;

        Course course = new Course(); //olhar padrao builder
        if (courseDTO.id() != null) course.setId(courseDTO.id());
        course.setName(courseDTO.name());
        course.setCategory(courseDTO.category());
        return course;
    }
}
