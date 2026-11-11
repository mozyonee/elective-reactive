package ua.com.reactive.elective.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ua.com.reactive.elective.entity.Student;

public interface StudentRepository extends ReactiveCrudRepository<Student, Long> {
}
