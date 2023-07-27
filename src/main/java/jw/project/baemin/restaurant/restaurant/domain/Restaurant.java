package jw.project.baemin.restaurant.restaurant.domain;

import static jw.project.baemin.restaurant.restaurant.domain.eums.ShopStatus.*;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.time.LocalTime;
import jw.project.baemin.restaurant.restaurant.domain.eums.OrderType;
import jw.project.baemin.restaurant.restaurant.domain.eums.ShopStatus;
import jw.project.baemin.restaurant.restaurant.domain.eums.SupportPayment;
import jw.project.baemin.restaurant.restaurant.presentation.request.UpdateRestaurantRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long ownerId;

    private String name;

    private String address;

    private LocalTime openTime;

    private LocalTime closeTime;

    @Enumerated(EnumType.STRING)
    private ShopStatus shopStatus;

    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    @Enumerated(EnumType.STRING)
    private SupportPayment supportedPayment;

    private String phoneNumber;

    private String description;

    private Double starRating;

    private Long reviewCount;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public void update(UpdateRestaurantRequest request) {
        this.address = request.address();
        this.openTime = request.openTime();
        this.closeTime = request.closeTime();
        this.description = request.description();
        this.phoneNumber = request.phoneNumber();
    }

    public void changeStatus() {
        if (this.shopStatus == OPEN) {
            this.shopStatus = CLOSED;
        } else {
            this.shopStatus = OPEN;
        }
    }

    public void changeOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public void changeSupportPayment(SupportPayment supportedPayment) {
        this.supportedPayment = supportedPayment;
    }
}
