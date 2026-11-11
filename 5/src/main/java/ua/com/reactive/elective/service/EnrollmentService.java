package ua.com.reactive.elective.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.com.reactive.elective.entity.Enrollment;
import ua.com.reactive.elective.repository.CourseRepository;
import ua.com.reactive.elective.repository.EnrollmentRepository;
import ua.com.reactive.elective.repository.StudentRepository;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository, StudentRepository studentRepository,
                             CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public Flux<Enrollment> list() {
        return enrollmentRepository.findAll();
    }

    public Mono<Enrollment> get(Long id) {
        return enrollmentRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    public Mono<Enrollment> create(Enrollment enrollment) {
        enrollment.setId(null);
        return requireLinks(enrollment).then(enrollmentRepository.save(enrollment));
    }

    public Mono<Enrollment> update(Long id, Enrollment enrollment) {
        return get(id).then(requireLinks(enrollment)).then(Mono.defer(() -> {
            enrollment.setId(id);
            return enrollmentRepository.save(enrollment);
        }));
    }

    public Mono<Void> delete(Long id) {
        return get(id).flatMap(enrollmentRepository::delete);
    }

    private Mono<Void> requireLinks(Enrollment enrollment) {
        if (enrollment.getStudentId() == null || enrollment.getCourseId() == null) {
            return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST));
        }
        return Mono.zip(
                        studentRepository.existsById(enrollment.getStudentId()),
                        courseRepository.existsById(enrollment.getCourseId()))
                .flatMap(found -> found.getT1() && found.getT2()
                        ? Mono.empty()
                        : Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST)));
    }
}
