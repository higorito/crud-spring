package com.curso_loiane.crud_spring.dto.mapper;

import com.curso_loiane.crud_spring.dto.CourseDTO;
import com.curso_loiane.crud_spring.dto.LessonDTO;
import com.curso_loiane.crud_spring.enums.Category;
import com.curso_loiane.crud_spring.model.Course;
import com.curso_loiane.crud_spring.model.Lesson;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CourseMapper {

    public CourseDTO toDTO(Course course){
        if (course == null) return null; //se for nulo, retorna nulo (pode ser que nao encontre o id... e ai da p famoso NULL POINTER EXCEPTION)
        //mapeamento padrao de lesson para lessonDTO -> numa lista
        List<LessonDTO> lessons = course.getLessons().stream()
                .map(lesson -> new LessonDTO(lesson.getId(), lesson.getName(), lesson.getYoutubeUrl()))
                .collect(Collectors.toList());


        return new CourseDTO(course.getId(),
                course.getName(),
                course.getCategory().getValue(), //esse getValue é o dentro do enum
                lessons
        );

    }

    public Course toEntity(CourseDTO courseDTO){
        if (courseDTO == null) return null;

        Course course = new Course(); //olhar padrao builder
        if (courseDTO.id() != null) course.setId(courseDTO.id());
        course.setName(courseDTO.name());
        course.setCategory(convertCategory(courseDTO.category()));

        List<Lesson> lessons = courseDTO.lessons().stream()
                .map(lessonDTO -> {
                    Lesson lesson = new Lesson();
                    lesson.setId(lessonDTO.id());
                    lesson.setName(lessonDTO.name());
                    lesson.setYoutubeUrl(lessonDTO.youtubeUrl());
                    lesson.setCourse(course);
                    return lesson;
                }).collect(Collectors.toList());

        course.setLessons(lessons);

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
