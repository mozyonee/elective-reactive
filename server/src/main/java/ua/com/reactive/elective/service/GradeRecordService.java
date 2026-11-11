package ua.com.reactive.elective.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.com.reactive.elective.entity.GradeRecord;
import ua.com.reactive.elective.repository.EnrollmentRepository;
import ua.com.reactive.elective.repository.GradeRecordRepository;

@Service
public class GradeRecordService {

    private final GradeRecordRepository gradeRecordRepository;
    private final EnrollmentRepository enrollmentRepository;

    public GradeRecordService(GradeRecordRepository gradeRecordRepository, EnrollmentRepository enrollmentRepository) {
        this.gradeRecordRepository = gradeRecordRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    public Flux<GradeRecord> list() {
        return gradeRecordRepository.findAll();
    }

    public Mono<GradeRecord> get(Long id) {
        return gradeRecordRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    public Mono<GradeRecord> create(GradeRecord gradeRecord) {
        gradeRecord.setId(null);
        return requireGrade(gradeRecord).then(gradeRecordRepository.save(gradeRecord));
    }

    public Mono<GradeRecord> update(Long id, GradeRecord gradeRecord) {
        return get(id).then(requireGrade(gradeRecord)).then(Mono.defer(() -> {
            gradeRecord.setId(id);
            return gradeRecordRepository.save(gradeRecord);
        }));
    }

    public Mono<Void> delete(Long id) {
        return get(id).flatMap(gradeRecordRepository::delete);
    }

    private Mono<Void> requireGrade(GradeRecord gradeRecord) {
        Integer grade = gradeRecord.getGrade();
        if (grade == null || grade < 0 || grade > 100 || gradeRecord.getEnrollmentId() == null) {
            return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST));
        }
        return enrollmentRepository.existsById(gradeRecord.getEnrollmentId())
                .flatMap(exists -> exists
                        ? Mono.empty()
                        : Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST)));
    }
}
