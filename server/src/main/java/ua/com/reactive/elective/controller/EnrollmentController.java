package ua.com.reactive.elective.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.com.reactive.elective.entity.Enrollment;
import ua.com.reactive.elective.service.EnrollmentService;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping
    public Flux<Enrollment> list() {
        return enrollmentService.list();
    }

    @GetMapping("/{id}")
    public Mono<Enrollment> one(@PathVariable Long id) {
        return enrollmentService.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Enrollment> create(@RequestBody Enrollment enrollment) {
        return enrollmentService.create(enrollment);
    }

    @PutMapping("/{id}")
    public Mono<Enrollment> update(@PathVariable Long id, @RequestBody Enrollment enrollment) {
        return enrollmentService.update(id, enrollment);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable Long id) {
        return enrollmentService.delete(id);
    }
}
