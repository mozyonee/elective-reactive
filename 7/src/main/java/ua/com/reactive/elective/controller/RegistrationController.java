package ua.com.reactive.elective.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;
import ua.com.reactive.elective.entity.RolesHasUsers;
import ua.com.reactive.elective.entity.User;
import ua.com.reactive.elective.repository.RoleRepository;
import ua.com.reactive.elective.repository.RolesHasUsersRepository;
import ua.com.reactive.elective.repository.UserRepository;

@RestController
public class RegistrationController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RolesHasUsersRepository rolesHasUsersRepository;

    public RegistrationController(UserRepository userRepository, RoleRepository roleRepository,
                                  RolesHasUsersRepository rolesHasUsersRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.rolesHasUsersRepository = rolesHasUsersRepository;
    }

    @PostMapping("/registration")
    public Mono<ResponseEntity<Void>> register(@RequestBody Registration registration) {
        return userRepository.findByUsername(registration.username())
                .flatMap(existing -> Mono.just(ResponseEntity.status(HttpStatus.CONFLICT).<Void>build()))
                .switchIfEmpty(Mono.defer(() -> userRepository
                        .save(new User(null, registration.username(), registration.password()))
                        .flatMap(saved -> roleRepository.findAll()
                                .filter(role -> "ROLE_User".equals(role.getName()))
                                .next()
                                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR)))
                                .flatMap(role -> rolesHasUsersRepository.save(
                                        new RolesHasUsers(null, role.getId(), saved.getId()))))
                        .thenReturn(ResponseEntity.ok().build())));
    }

    public record Registration(String username, String password) {
    }
}
