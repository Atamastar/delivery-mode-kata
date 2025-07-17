package crafteam.kata.delivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import crafteam.kata.delivery.application.service.DeliveryService;
import crafteam.kata.delivery.domain.port.in.DeliveryUseCase;
import crafteam.kata.delivery.domain.port.out.OrderRepository;
import crafteam.kata.delivery.infra.adapter.out.persistance.InMemoryOrderRepository;

@SpringBootApplication
public class DeliveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeliveryApplication.class, args);
	}

	@Bean
	public OrderRepository orderRepository() {
		return new InMemoryOrderRepository();
	}

	@Bean
	public DeliveryUseCase deliveryUseCase(OrderRepository orderRepository) {
		return new DeliveryService(orderRepository);
	}


}
