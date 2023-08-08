package jw.project.baemin.payment.domain;

import static jw.project.baemin.payment.domain.enums.PaymentStatus.*;

import jakarta.persistence.Entity;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import jw.project.baemin.customer.domain.Customer;
import jw.project.baemin.order.domain.Order;
import jw.project.baemin.order.domain.enums.PaymentType;
import jw.project.baemin.payment.domain.enums.PaymentStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

@Entity
@Getter
@Table
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int price;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private PaymentStatus paymentStatus;

    private PaymentType paymentType;

    @OneToOne(mappedBy = "payment")
    private Order order;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public static Payment createPayment(Customer customer, Order order, PaymentType paymentType,
        int price) {
        return Payment.builder()
            .customer(customer)
            .order(order)
            .paymentStatus(NOT_PAYED)
            .paymentType(paymentType)
            .price(price)
            .build();
    }

    public void changeStatus(@NonNull PaymentStatus status) {
        this.paymentStatus = status;
    }

    public void registerOrder(@NonNull Order order) {
        if (this.order != null) {
            throw new IllegalArgumentException("Payment already registered order.");
        }
        this.order = order;
    }

    @Builder
    public Payment(Long id, int price, Customer customer, PaymentStatus paymentStatus,
        PaymentType paymentType, Order order, LocalDateTime createdAt) {
        this.id = id;
        this.price = price;
        this.customer = customer;
        this.paymentStatus = paymentStatus;
        this.paymentType = paymentType;
        this.order = order;
        this.createdAt = createdAt;
    }
}
