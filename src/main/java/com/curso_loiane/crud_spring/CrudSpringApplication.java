package com.curso_loiane.crud_spring;

import com.curso_loiane.crud_spring.enums.Category;
import com.curso_loiane.crud_spring.model.Course;
import com.curso_loiane.crud_spring.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner initDataBase(CourseRepository repository) {
		return args ->{
			repository.deleteAll();

			Course course = new Course();
			course.setName("Java");
			course.setCategory(Category.BACKEND);
			repository.save(course);

			Course course2 = new Course();
			course2.setName("React");
			course2.setCategory(Category.FRONTEND);
			repository.save(course2);

		};
	}
}