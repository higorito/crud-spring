package com.curso_loiane.crud_spring;

import com.curso_loiane.crud_spring.enums.Category;
import com.curso_loiane.crud_spring.model.Course;
import com.curso_loiane.crud_spring.model.Lesson;
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

			Lesson lesson1 = new Lesson();
			lesson1.setName("Java OO");
			lesson1.setYoutubeUrl("e.com/watch?");

			lesson1.setCourse(course);

			course.getLessons().add(lesson1);

			repository.save(course);
//----------------------------------------------------------------------
			Course course2 = new Course();
			course2.setName("React");
			course2.setCategory(Category.FRONTEND);

			Lesson lesson2 = new Lesson();
			lesson2.setName("React Hooks");
			lesson2.setYoutubeUrl(".com/watch?");

			lesson2.setCourse(course2);

			course2.getLessons().add(lesson2);

			repository.save(course2);



		};
	}
}