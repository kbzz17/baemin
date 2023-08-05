package jw.project.baemin.delivery.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;
import jw.project.baemin.order.domain.Order;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class Delivery {
    @Id
    private Long id;

    private String restaurantAddress;

    private String customerAddress;

    private int deliveryFee;

    private LocalDateTime arrivalTime;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    public static Delivery createDelivery(Order order, String restaurantAddress,
        String customerAddress, int deliveryFee){
        return Delivery.builder()
            .order(order)
            .restaurantAddress(restaurantAddress)
            .customerAddress(customerAddress)
            .deliveryFee(deliveryFee)
            .build();
    }

    public Delivery() {
    }

    @Builder
    public Delivery(Long id, String restaurantAddress, String customerAddress, int deliveryFee,
        LocalDateTime arrivalTime, Order order) {
        this.id = id;
        this.restaurantAddress = restaurantAddress;
        this.customerAddress = customerAddress;
        this.deliveryFee = deliveryFee;
        this.arrivalTime = arrivalTime;
        this.order = order;
    }
}
