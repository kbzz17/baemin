package jw.project.baemin.restaurant.restaurant.presentation.request;

public record AddDeliveryRegionRequest(Long restaurantId, Long regionCodeId, Integer deliveryFee) {

}
