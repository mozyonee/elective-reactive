package ua.com.reactive.elective.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import ua.com.reactive.elective.entity.RolesHasUsers;

public interface RolesHasUsersRepository extends ReactiveCrudRepository<RolesHasUsers, Long> {

    @Query("SELECT role_id FROM roles_has_users WHERE user_id = :userId")
    Flux<Long> findRoleIdsByUserId(Long userId);
}
