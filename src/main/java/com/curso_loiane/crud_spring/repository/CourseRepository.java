package com.curso_loiane.crud_spring.repository;

import com.curso_loiane.crud_spring.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    //quando extende o JpaRepository, ele ja tem os metodos prontos
    //findAll, findById, save, delete
}
