package jw.project.baemin.order.presentation.request;

import java.util.List;
import jw.project.baemin.order.domain.OrderItem;

public record CreateOrderRequest(
    Long customerId, Long restaurantId, Long couponId, String requests, String paymentType,
    Integer deliveryFee, List<OrderItem> items
) {

}
