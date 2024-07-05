package com.curso_loiane.crud_spring;

import com.curso_loiane.crud_spring.enums.Category;
import com.curso_loiane.crud_spring.model.Course;
import com.curso_loiane.crud_spring.model.Lesson;
import com.curso_loiane.crud_spring.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean
	@Profile("dev")  // só vai rodar quando o profile for dev
	CommandLineRunner initDataBase(CourseRepository repository) {
		return args ->{
			repository.deleteAll();

			for (int i = 0; i < 10; i++) {
				Course course = new Course();
				course.setName("Spring Boot" + i);
				course.setCategory(Category.BACKEND);

				Lesson lesson = new Lesson();
				lesson.setName("Rest API" + i);
				lesson.setYoutubeUrl(".com/watch?");

				lesson.setCourse(course);

				course.getLessons().add(lesson);


				repository.save(course);
			}
//----------------------------------------------------------------------
			Course course2 = new Course();
			course2.setName("React");
			course2.setCategory(Category.FRONTEND);

			Lesson lesson2 = new Lesson();
			lesson2.setName("React Hooks");
			lesson2.setYoutubeUrl(".com/watch?");

			lesson2.setCourse(course2);

			course2.getLessons().add(lesson2);

			Lesson lesson3 = new Lesson();
			lesson3.setName("React Context");
			lesson3.setYoutubeUrl(".com/watch?");
			lesson3.setCourse(course2);
			course2.getLessons().add(lesson3);

			repository.save(course2);



		};
	}
}