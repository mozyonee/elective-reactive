package ua.com.reactive.elective.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ua.com.reactive.elective.entity.Teacher;

public interface TeacherRepository extends ReactiveCrudRepository<Teacher, Long> {
}
