package ua.com.reactive.elective.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import ua.com.reactive.elective.entity.Role;

public interface RoleRepository extends ReactiveCrudRepository<Role, Long> {

    @Query("SELECT id, name FROM roles WHERE id = :id")
    Mono<Role> findById(Long id);
}
