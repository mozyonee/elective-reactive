package ua.com.reactive.elective.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.com.reactive.elective.entity.Student;
import ua.com.reactive.elective.repository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Flux<Student> list() {
        return studentRepository.findAll();
    }

    public Mono<Student> get(Long id) {
        return studentRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    public Mono<Student> create(Student student) {
        student.setId(null);
        return studentRepository.save(student);
    }

    public Mono<Student> update(Long id, Student student) {
        return get(id).then(Mono.defer(() -> {
            student.setId(id);
            return studentRepository.save(student);
        }));
    }

    public Mono<Void> delete(Long id) {
        return get(id).flatMap(studentRepository::delete);
    }
}
