package com.curso_loiane.crud_spring.service;

import com.curso_loiane.crud_spring.dto.CourseDTO;
import com.curso_loiane.crud_spring.dto.CoursePageDTO;
import com.curso_loiane.crud_spring.dto.mapper.CourseMapper;
import com.curso_loiane.crud_spring.exception.RecordNotFoundException;
import com.curso_loiane.crud_spring.model.Course;
import com.curso_loiane.crud_spring.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

   /* public List<CourseDTO> list(){
        //findAll retorna um iterable, entao transforma em stream faz o map convertendo para dto
        //e depois pega tod0 objeto retornado e coloca em uma lista
        return courseRepository.findAll()
                .stream()
                .map(courseMapper::toDTO)
                .collect(Collectors.toList());
    }*/

    //COMO o service é publico é legal colocar as validações aqui TAMBEM, pois pode ter outro controller chamando esse metodo
    public CoursePageDTO list(@PositiveOrZero int page, @Positive @Max(100) int size){
        Page<Course> pageCourse = courseRepository.findAll(PageRequest.of(page, size));

        List<CourseDTO> courseDTOList = pageCourse.get()
                .map(courseMapper::toDTO)
                .collect(Collectors.toList());

        return new CoursePageDTO(courseDTOList, pageCourse.getTotalPages(), pageCourse.getTotalElements());
    }

    public CourseDTO findById(@NotNull @Positive Long id)  {
        return courseRepository.findById(id).map(courseMapper::toDTO) //se encontrar o id, mapeia para dto
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public CourseDTO create(@Valid CourseDTO course){
        //no save ele pega o dto e converte para entidade
        return courseMapper.toDTO( courseRepository.save(courseMapper.toEntity(course))); //ja pega o objeto de retorno e converte para dto
    }

    public CourseDTO update(@NotNull @Positive Long id, @Valid CourseDTO courseDTO){
        return courseRepository.findById(id)
                .map(recordFound -> {
                    Course course = courseMapper.toEntity(courseDTO); //converte o dto para entidade e nisso ja vem as lessons
                    recordFound.setName(courseDTO.name());
                    recordFound.setCategory(this.courseMapper.convertCategory(courseDTO.category()));
                    recordFound.getLessons().clear(); //limpa essa lista e depois adiciona as lessons do dto. senao ele ia perder a referencia
                    course.getLessons().forEach(lesson -> {
                        recordFound.getLessons().add(lesson);
                    });

                    return courseMapper.toDTO(courseRepository.save(recordFound));
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@NotNull @Positive Long id)  {
        //busca a entidade(delete precisa dela) pelo id e se nao encontrar lança a exceção
        courseRepository.delete(courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
    }

}
