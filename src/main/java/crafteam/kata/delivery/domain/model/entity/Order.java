package crafteam.kata.delivery.domain.model.entity;

import java.util.Objects;

import crafteam.kata.delivery.domain.model.enums.DeliveryMode;

public class Order {
    private String id;
    private String customerId;
    private String address;
    private String phoneNumber;
    private String comment;
    private boolean isPaid;
    private boolean isDelivered;
    private DeliveryMode deliveryMode;

        public Order() {
                // Default constructor
        }

    public Order(String id, String customerId, String address, String phoneNumber, String comment) {
        this.id = id;
        this.customerId = customerId;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.comment = comment;
        this.isPaid = false;
        this.isDelivered = false;
        this.deliveryMode = DeliveryMode.DELIVERY; // Default delivery mode
    }

    public String getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getComment() {
        return comment;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public DeliveryMode getDeliveryMode() {
        return deliveryMode;
    }
    public void setId(String id) {
        this.id = id;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setDeliveryMode(DeliveryMode deliveryMode) {
        this.deliveryMode = deliveryMode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
