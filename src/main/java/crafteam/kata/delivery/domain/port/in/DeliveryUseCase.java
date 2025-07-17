package crafteam.kata.delivery.domain.port.in;

import crafteam.kata.delivery.domain.model.entity.Order;
import reactor.core.publisher.Mono;

public interface DeliveryUseCase {
    Mono<Order> findById(String orderId);
    Mono<Void> chooseDeliveryMode(String orderId,  String deliveryMode);
}
