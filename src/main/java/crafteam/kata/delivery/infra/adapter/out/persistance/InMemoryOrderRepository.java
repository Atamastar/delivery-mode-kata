package crafteam.kata.delivery.infra.adapter.out.persistance;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import crafteam.kata.delivery.domain.model.entity.Order;
import crafteam.kata.delivery.domain.port.out.OrderRepository;
import reactor.core.publisher.Mono;

public class InMemoryOrderRepository implements OrderRepository {

    private final Map<String, Order> db = new ConcurrentHashMap<>();

    @Override
    public Mono<Order> findById(String orderId) {
        init(); // Initialize the repository with a sample order, for testing purposes
        return Mono.justOrEmpty(db.get(orderId));
    }

    @Override
    public Mono<Order> save(Order order) {
        db.put(order.getId(), order);
        return Mono.just(order);
    }

     void init() {
        if (!db.containsKey("1")) {
            Order order = new Order("1", "10001", "10 rue du Test", "0600000000", "livrer vite");
            db.put(order.getId(), order);
        }
    }
}
