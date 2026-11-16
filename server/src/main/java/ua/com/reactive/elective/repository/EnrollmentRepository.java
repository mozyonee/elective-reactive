package ua.com.reactive.elective.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ua.com.reactive.elective.entity.Enrollment;

public interface EnrollmentRepository extends ReactiveCrudRepository<Enrollment, Long> {
}
