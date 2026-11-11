package ua.com.reactive.elective.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ua.com.reactive.elective.entity.GradeRecord;

public interface GradeRecordRepository extends ReactiveCrudRepository<GradeRecord, Long> {
}
