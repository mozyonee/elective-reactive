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
import ua.com.reactive.elective.entity.GradeRecord;
import ua.com.reactive.elective.service.GradeRecordService;

@RestController
@RequestMapping("/grades")
public class GradeController {

    private final GradeRecordService gradeRecordService;

    public GradeController(GradeRecordService gradeRecordService) {
        this.gradeRecordService = gradeRecordService;
    }

    @GetMapping
    public Flux<GradeRecord> list() {
        return gradeRecordService.list();
    }

    @GetMapping("/{id}")
    public Mono<GradeRecord> one(@PathVariable Long id) {
        return gradeRecordService.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<GradeRecord> create(@RequestBody GradeRecord gradeRecord) {
        return gradeRecordService.create(gradeRecord);
    }

    @PutMapping("/{id}")
    public Mono<GradeRecord> update(@PathVariable Long id, @RequestBody GradeRecord gradeRecord) {
        return gradeRecordService.update(id, gradeRecord);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable Long id) {
        return gradeRecordService.delete(id);
    }
}
