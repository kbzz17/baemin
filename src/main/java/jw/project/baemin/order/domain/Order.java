package jw.project.baemin.order.domain;

import static jw.project.baemin.order.domain.enums.OrderStatus.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import jw.project.baemin.customer.domain.Coupon;
import jw.project.baemin.customer.domain.Customer;
import jw.project.baemin.delivery.domain.Delivery;
import jw.project.baemin.order.domain.enums.OrderStatus;
import jw.project.baemin.order.domain.enums.PaymentType;
import jw.project.baemin.restaurant.restaurant.domain.Restaurant;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "orders")
@Getter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    private String requests;

    private Integer orderPrice;

    private Integer deliveryFee;

    private Integer discountPrice;

    private Integer finalPrice;

    private String deliveryAddress;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @CreationTimestamp
    private LocalDateTime orderedTime;

    public static Order createOrder(Customer customer, Restaurant restaurant, Coupon coupon,
        List<OrderItem> orderItems, String requests, PaymentType paymentType, Integer deliveryFee,
        String deliveryAddress) {

        int orderPrice = orderItems.stream()
            .mapToInt(item -> item.getMenu().getPrice() * item.getCount())
            .sum();

        int finalPrice = coupon.calculateDiscount(orderPrice + deliveryFee);

        return Order.builder()
            .customer(customer)
            .restaurant(restaurant)
            .orderItems(orderItems)
            .coupon(coupon)
            .requests(requests)
            .orderPrice(orderPrice)
            .deliveryFee(deliveryFee)
            .discountPrice(orderPrice + deliveryFee - finalPrice)
            .finalPrice(finalPrice)
            .deliveryAddress(deliveryAddress)
            .paymentType(paymentType)
            .orderStatus(ORDERED)
            .build();
    }

    public void acceptOrder(Delivery delivery) {
        this.delivery = delivery;
        this.orderStatus = ACCEPTED;
    }

    public void cancelOrder() {
        if (this.coupon != null) {
            coupon = null;
        }
        this.orderStatus = CANCELED;
    }

    public Order() {
    }

    @Builder
    public Order(Long id, Customer customer, Restaurant restaurant, Coupon coupon,
        Delivery delivery,
        List<OrderItem> orderItems, String requests, Integer orderPrice, Integer deliveryFee,
        Integer discountPrice, Integer finalPrice, String deliveryAddress, PaymentType paymentType,
        OrderStatus orderStatus, LocalDateTime orderedTime) {
        this.id = id;
        this.customer = customer;
        this.restaurant = restaurant;
        this.coupon = coupon;
        this.delivery = delivery;
        this.orderItems.addAll(orderItems);
        this.requests = requests;
        this.orderPrice = orderPrice;
        this.deliveryFee = deliveryFee;
        this.discountPrice = discountPrice;
        this.finalPrice = finalPrice;
        this.deliveryAddress = deliveryAddress;
        this.paymentType = paymentType;
        this.orderStatus = orderStatus;
        this.orderedTime = orderedTime;
    }
}
