package ua.com.reactive.elective.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.com.reactive.elective.repository.RoleRepository;
import ua.com.reactive.elective.repository.RolesHasUsersRepository;
import ua.com.reactive.elective.repository.UserRepository;

import java.util.HashSet;

@Service
public class UserService implements ReactiveUserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RolesHasUsersRepository rolesHasUsersRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository,
                       RolesHasUsersRepository rolesHasUsersRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.rolesHasUsersRepository = rolesHasUsersRepository;
    }

    public Flux<GrantedAuthority> getAuthorities(Long userId) {
        return rolesHasUsersRepository.findRoleIdsByUserId(userId)
                .flatMap(roleRepository::findById)
                .cast(GrantedAuthority.class);
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return userRepository.findByUsername(username)
                .flatMap(user -> getAuthorities(user.getId())
                        .collectList()
                        .map(authorities -> {
                            user.setAuthorities(new HashSet<>(authorities));
                            return user;
                        }));
    }
}
