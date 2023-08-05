package jw.project.baemin.order.presentation.request;

public record CreateOrderRequest(
    Long customerId, Long restaurantId, Long couponId, String requests, String paymentType,
    Integer deliveryFee
) {

}
