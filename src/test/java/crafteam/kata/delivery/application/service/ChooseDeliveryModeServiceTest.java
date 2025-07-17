package crafteam.kata.delivery.application.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import crafteam.kata.delivery.domain.exception.OrderNotFoundException;
import crafteam.kata.delivery.domain.model.entity.Order;
import crafteam.kata.delivery.domain.port.out.OrderRepository;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class ChooseDeliveryModeServiceTest {
	
	private OrderRepository repository;
    private DeliveryService service;

    @BeforeEach
    void setUp() {
        repository = mock(OrderRepository.class);
        service = new DeliveryService(repository);
    }


    @Test
    void should_change_delivery_mode_successfully() {
        Order order = new Order("1", "10001", "addr", "0611111123", "comment");
        when(repository.findById("1")).thenReturn(Mono.just(order));
        when(repository.save(order)).thenReturn(Mono.just(order));

        StepVerifier.create(service.chooseDeliveryMode("1", "DRIVE"))
            .verifyComplete();

        assert(order.getDeliveryMode().name().equals("DRIVE"));
        verify(repository).save(order);
    }

    @Test
    void should_fail_on_invalid_order_id() {
        when(repository.findById("2")).thenReturn(Mono.empty());
		
        StepVerifier.create(service.chooseDeliveryMode("2",  "DRIVE"))
            .expectErrorMatches(e -> e instanceof OrderNotFoundException)
            .verify();
    }

    @Test
    void should_fail_on_invalid_delivery_mode() {
        when(repository.findById("1")).thenReturn(Mono.just(new Order("1", "10001", "addr", "0611111123", "comment")));

        StepVerifier.create(service.chooseDeliveryMode("1",  "UNKNOWN_MODE"))
            .expectErrorMatches(e -> e instanceof IllegalArgumentException && e.getMessage().equals("Invalid delivery mode"))
            .verify();
    }
	

}

