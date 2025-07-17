package crafteam.kata.delivery.domain.exception;

public class OrderNotFoundException extends RuntimeException {
    private final String orderId;

    public OrderNotFoundException(String orderId) {
        super("Order not found: " + orderId);
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    @Override
    public String toString() {
        return "OrderNotFoundException{" +
                "orderId='" + orderId + '\'' +
                '}';
    }
}
