package ua.com.reactive.elective.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ua.com.reactive.elective.entity.Client;

@RestController
public class MyController {

    @GetMapping("/clients")
    public Flux<Client> getClients() {
        return Flux.just(
                        new Client(1L, "Daniel", "Brooks", 18),
                        new Client(2L, "Elena", "Navarro", 19),
                        new Client(3L, "Sofia", "Berg", 20)
                )
                .skip(0)
                .take(2);
    }
}
