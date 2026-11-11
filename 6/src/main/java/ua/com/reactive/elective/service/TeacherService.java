package ua.com.reactive.elective.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.com.reactive.elective.entity.Teacher;
import ua.com.reactive.elective.repository.TeacherRepository;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public Flux<Teacher> list() {
        return teacherRepository.findAll();
    }

    public Mono<Teacher> get(Long id) {
        return teacherRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    public Mono<Teacher> create(Teacher teacher) {
        teacher.setId(null);
        return teacherRepository.save(teacher);
    }

    public Mono<Teacher> update(Long id, Teacher teacher) {
        return get(id).then(Mono.defer(() -> {
            teacher.setId(id);
            return teacherRepository.save(teacher);
        }));
    }

    public Mono<Void> delete(Long id) {
        return get(id).flatMap(teacherRepository::delete);
    }
}
