package ua.com.reactive.elective.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ua.com.reactive.elective.entity.Message;

public interface MessageRepository extends ReactiveCrudRepository<Message, Long> {
}
