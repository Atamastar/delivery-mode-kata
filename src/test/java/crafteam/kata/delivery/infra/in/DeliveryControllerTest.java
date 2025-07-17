package crafteam.kata.delivery.infra.in;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.reactive.server.WebTestClient;

import crafteam.kata.delivery.application.service.DeliveryService;
import crafteam.kata.delivery.infra.adapter.in.web.OrderController;
import reactor.core.publisher.Mono;

@WebFluxTest(OrderController.class)
class DeliveryControllerTest {
    @Autowired DeliveryService deliveryService;

    @Autowired WebTestClient webClient;

    @TestConfiguration
    static class MockConfig {
        @Bean
        public DeliveryService deliveryService() {
            return mock(DeliveryService.class);
        }
    }

    @Test
    void should_return_200_when_success() {
        when(deliveryService.chooseDeliveryMode("1", "DRIVE")).thenReturn(Mono.empty());

        webClient.post().uri("/orders/1/delivery-mode?deliveryMode=DRIVE")
            .exchange()
            .expectStatus().isOk();
    }
}
