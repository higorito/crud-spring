package com.curso_loiane.crud_spring.dto.mapper;

import com.curso_loiane.crud_spring.dto.CourseDTO;
import com.curso_loiane.crud_spring.enums.Category;
import com.curso_loiane.crud_spring.model.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public CourseDTO toDTO(Course course){
        if (course == null) return null; //se for nulo, retorna nulo (pode ser que nao encontre o id... e ai da p famoso NULL POINTER EXCEPTION)
        return new CourseDTO(course.getId(), course.getName(), course.getCategory().getValue(), course.getLessons());
                                                                                //esse getValue é o dentro do enum, mt util
    }

    public Course toEntity(CourseDTO courseDTO){
        if (courseDTO == null) return null;

        Course course = new Course(); //olhar padrao builder
        if (courseDTO.id() != null) course.setId(courseDTO.id());
        course.setName(courseDTO.name());
        course.setCategory(convertCategory(courseDTO.category()));
        return course;
    }

    public Category convertCategory(String category){
        if (category == null) return null;

        return switch (category){
            case "Backend" -> Category.BACKEND;
            case "Frontend" -> Category.FRONTEND;
            case "Mobile" -> Category.MOBILE;
            default -> throw new IllegalArgumentException("Categoria inválida: " + category);
        };

    }
}
