package jw.project.baemin.restaurant.restaurant.presentation.request;

import java.time.LocalTime;
import jw.project.baemin.restaurant.restaurant.domain.Restaurant;
import jw.project.baemin.restaurant.restaurant.domain.eums.OrderType;
import jw.project.baemin.restaurant.restaurant.domain.eums.ShopStatus;
import jw.project.baemin.restaurant.restaurant.domain.eums.SupportPayment;

public record CreateRestaurantRequest(Long ownerId, String name, String address, LocalTime openTime,
                                      LocalTime closeTime, Boolean isOpen, String phoneNumber,
                                      String description, Double starRating, Long reviewCount,
                                      OrderType orderType, SupportPayment supportPayment) {

    public Restaurant toEntity() {
        return Restaurant.builder()
            .ownerId(this.ownerId)
            .name(this.name)
            .address(this.address)
            .openTime(this.openTime)
            .closeTime(this.closeTime)
            .orderType(this.orderType)
            .supportedPayment(this.supportPayment)
            .shopStatus(ShopStatus.CLOSED)
            .phoneNumber(this.phoneNumber)
            .description(this.description)
            .starRating(this.starRating)
            .reviewCount(this.reviewCount)
            .build();
    }
}
