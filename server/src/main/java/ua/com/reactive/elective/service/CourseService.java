package ua.com.reactive.elective.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.com.reactive.elective.entity.Course;
import ua.com.reactive.elective.repository.CourseRepository;
import ua.com.reactive.elective.repository.TeacherRepository;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    public CourseService(CourseRepository courseRepository, TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
    }

    public Flux<Course> list() {
        return courseRepository.findAll();
    }

    public Mono<Course> get(Long id) {
        return courseRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    public Mono<Course> create(Course course) {
        course.setId(null);
        return requireTeacher(course.getTeacherId()).then(courseRepository.save(course));
    }

    public Mono<Course> update(Long id, Course course) {
        return get(id).then(requireTeacher(course.getTeacherId())).then(Mono.defer(() -> {
            course.setId(id);
            return courseRepository.save(course);
        }));
    }

    public Mono<Void> delete(Long id) {
        return get(id).flatMap(courseRepository::delete);
    }

    private Mono<Void> requireTeacher(Long teacherId) {
        if (teacherId == null) {
            return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST));
        }
        return teacherRepository.existsById(teacherId)
                .flatMap(exists -> exists
                        ? Mono.empty()
                        : Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST)));
    }
}
