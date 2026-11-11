package ua.com.reactive.elective.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ua.com.reactive.elective.entity.Course;

public interface CourseRepository extends ReactiveCrudRepository<Course, Long> {
}
