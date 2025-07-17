package crafteam.kata.delivery.application.service;

import crafteam.kata.delivery.domain.exception.OrderNotFoundException;
import crafteam.kata.delivery.domain.model.entity.Order;
import crafteam.kata.delivery.domain.model.enums.DeliveryMode;
import crafteam.kata.delivery.domain.port.in.DeliveryUseCase;
import crafteam.kata.delivery.domain.port.out.OrderRepository;
import reactor.core.publisher.Mono;

public class DeliveryService implements DeliveryUseCase {

    private final OrderRepository orderRepository;

    public DeliveryService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Mono<Order> findById(String orderId) {
        return orderRepository.findById(orderId)
                .switchIfEmpty(Mono.error(new OrderNotFoundException(orderId)));
    }

    @Override
    public Mono<Void> chooseDeliveryMode(String orderId, String deliveryMode) {
        return orderRepository.findById(orderId)
                .switchIfEmpty(Mono.error(new OrderNotFoundException(orderId)))
                .flatMap(order -> {
                       DeliveryMode mode;
                        try {
                            mode = DeliveryMode.valueOf(deliveryMode);
                        } catch (IllegalArgumentException e) {
                            return Mono.error(new IllegalArgumentException("Invalid delivery mode"));
                        }
                    order.setDeliveryMode(mode);
                    return orderRepository.save(order);
                })
                .then();
    }

}
