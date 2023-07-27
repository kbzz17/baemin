package jw.project.baemin.restaurant.restaurant.presentation.response;

import java.time.LocalTime;
import jw.project.baemin.restaurant.restaurant.domain.Restaurant;
import jw.project.baemin.restaurant.restaurant.domain.eums.OrderType;
import jw.project.baemin.restaurant.restaurant.domain.eums.ShopStatus;
import jw.project.baemin.restaurant.restaurant.domain.eums.SupportPayment;
import lombok.Builder;

@Builder
public record RestaurantResponse(Long id, Long ownerId, String name, String address,
                                 LocalTime openTime, LocalTime closeTime, Boolean isOpen,
                                 String phoneNumber, String description, Double starRating,
                                 Long reviewCount, OrderType orderType,
                                 SupportPayment supportPayment, ShopStatus shopStatus
) {

    public static RestaurantResponse from(Restaurant restaurant) {
        return RestaurantResponse.builder()
            .id(restaurant.getId())
            .ownerId(restaurant.getOwnerId())
            .name(restaurant.getName())
            .address(restaurant.getAddress())
            .openTime(restaurant.getOpenTime())
            .closeTime(restaurant.getCloseTime())
            .shopStatus(restaurant.getShopStatus())
            .orderType(restaurant.getOrderType())
            .supportPayment(restaurant.getSupportedPayment())
            .phoneNumber(restaurant.getPhoneNumber())
            .description(restaurant.getDescription())
            .starRating(restaurant.getStarRating())
            .reviewCount(restaurant.getReviewCount())
            .build();
    }
}
