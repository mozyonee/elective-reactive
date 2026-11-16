package ua.com.reactive.elective.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import ua.com.reactive.elective.service.UserService;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class WebSecurityConfig {

    private static final String[] RESOURCES = {"/teachers", "/teachers/**", "/courses", "/courses/**",
            "/students", "/students/**", "/enrollments", "/enrollments/**", "/grades", "/grades/**"};

    private final UserService userService;

    public WebSecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    @SuppressWarnings("deprecation")
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public ReactiveAuthenticationManager authenticationManager(PasswordEncoder passwordEncoder) {
        UserDetailsRepositoryReactiveAuthenticationManager manager =
                new UserDetailsRepositoryReactiveAuthenticationManager(userService);
        manager.setPasswordEncoder(passwordEncoder);
        return manager;
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http,
                                                            ReactiveAuthenticationManager authenticationManager) {
        return http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(List.of("http://localhost:8081"));
                    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    config.setAllowedHeaders(List.of("*"));
                    config.setAllowCredentials(true);
                    return config;
                }))
                .authorizeExchange(exchange -> exchange
                        .pathMatchers(HttpMethod.OPTIONS).permitAll()
                        .pathMatchers(HttpMethod.GET, "/", "/hello", "/clients").permitAll()
                        .pathMatchers(HttpMethod.POST, "/registration").permitAll()
                        .pathMatchers(HttpMethod.GET, "/admin").hasRole("Admin")
                        .pathMatchers(HttpMethod.GET, "/users").hasRole("User")
                        .pathMatchers(HttpMethod.POST, RESOURCES).hasRole("Admin")
                        .pathMatchers(HttpMethod.PUT, RESOURCES).hasRole("Admin")
                        .pathMatchers(HttpMethod.DELETE, RESOURCES).hasRole("Admin")
                        .pathMatchers(HttpMethod.GET, RESOURCES).authenticated()
                        .anyExchange().authenticated())
                .exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint((exchange, ex) -> {
                            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                            return exchange.getResponse().setComplete();
                        })
                        .accessDeniedHandler((exchange, denied) -> {
                            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                            return exchange.getResponse().setComplete();
                        }))
                .formLogin(withDefaults())
                .authenticationManager(authenticationManager)
                .build();
    }
}
