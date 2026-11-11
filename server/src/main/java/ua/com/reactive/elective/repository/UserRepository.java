package ua.com.reactive.elective.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import ua.com.reactive.elective.entity.User;

public interface UserRepository extends ReactiveCrudRepository<User, Long> {

    @Query("SELECT id, username, password FROM users WHERE username = :username")
    Mono<User> findByUsername(String username);
}
