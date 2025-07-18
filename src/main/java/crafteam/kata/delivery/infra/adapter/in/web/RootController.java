package crafteam.kata.delivery.infra.adapter.in.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class RootController {

    @GetMapping("/")
    public Mono<String> root() {
        return Mono.just("🚀 REST server running");
    }
}

