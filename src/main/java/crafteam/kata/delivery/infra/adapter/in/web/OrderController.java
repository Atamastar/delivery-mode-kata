package crafteam.kata.delivery.infra.adapter.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import crafteam.kata.delivery.domain.exception.OrderNotFoundException;
import crafteam.kata.delivery.domain.model.entity.Order;
import crafteam.kata.delivery.domain.port.in.DeliveryUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@Tag(name = "Order Management", description = "Operations related to order management")
public class OrderController {

    private final DeliveryUseCase deliveryUseCase;

    public OrderController(DeliveryUseCase deliveryUseCase) {
        this.deliveryUseCase = deliveryUseCase;
    }

    @GetMapping("/{orderId}")
    @Operation(summary = "Get Order by ID", description = "Retrieves an order by its ID")
    @ApiResponse(responseCode = "404", description = "Order not found")
    public Mono<ResponseEntity<Order>> getOrderById(@PathVariable @NotBlank String orderId) {
        return deliveryUseCase.findById(orderId)
                .map(order -> ResponseEntity.ok(order))
                .onErrorResume(OrderNotFoundException.class, ex ->
                        Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build())
                );
    }
    @Operation(summary = "Choose Delivery Mode", description = "Allows a user to choose a delivery mode for an order")
    @PostMapping("/{orderId}/delivery-mode")
    public Mono<Void> chooseDeliveryMode(@PathVariable @NotBlank String orderId, @RequestParam @NotBlank @Pattern(regexp = "^(DRIVE|DELIVERY|DELIVERY_TODAY|DELIVERY_ASAP)$") String deliveryMode) {
        return deliveryUseCase.chooseDeliveryMode(orderId, deliveryMode);
    }

}
