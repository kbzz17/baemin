package jw.project.baemin.support;

import java.time.LocalTime;
import jw.project.baemin.restaurant.restaurant.domain.Restaurant;
import jw.project.baemin.restaurant.restaurant.presentation.request.CreateRestaurantRequest;
import jw.project.baemin.restaurant.restaurant.presentation.request.UpdateRestaurantRequest;

public class RestaurantSupport {

    private static final Long ownerId = 1L;
    private static final String name = "평양냉면 맛집";

    private static final String address = "서울특별시 종로구 종로1가동 지하상가";
    private static final LocalTime openTime = LocalTime.MIN;
    private static final LocalTime closeTime = LocalTime.MAX;
    private static final Boolean isOpen = false;
    private static final String description = "very Delicious";
    private static final String phoneNumber = "01112341234";
    private static final Double starRating = 4.9D;
    private static final Long reviewCount = 500L;

    public static Restaurant get(Long id) {
        return Restaurant.builder()
            .id(1L)
            .ownerId(ownerId)
            .name(name)
            .address(address)
            .openTime(openTime)
            .closeTime(closeTime)
            .isOpen(isOpen)
            .phoneNumber(phoneNumber)
            .description(description)
            .starRating(starRating)
            .reviewCount(reviewCount)
            .build();
    }

    public static CreateRestaurantRequest getCreateRestaurantRequest() {
        return new CreateRestaurantRequest(ownerId, name, address, openTime, closeTime, isOpen,
            phoneNumber, description, starRating, reviewCount);
    }

    public static UpdateRestaurantRequest getUpdateRestaurantRequest() {
        return new UpdateRestaurantRequest(LocalTime.NOON,
            LocalTime.MIDNIGHT, "01012345678", "서울시 강남구 역삼동", "아이우에오");
    }
}
