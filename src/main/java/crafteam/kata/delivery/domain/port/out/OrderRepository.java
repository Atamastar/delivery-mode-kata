package crafteam.kata.delivery.domain.port.out;


import crafteam.kata.delivery.domain.model.entity.Order;
import reactor.core.publisher.Mono;

public interface OrderRepository  {
    Mono<Order> findById(String orderId);
    Mono<Order> save(Order order);
}
