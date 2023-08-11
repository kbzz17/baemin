package jw.project.baemin.order.presentation;

import java.util.List;
import jw.project.baemin.common.ApiResponse;
import jw.project.baemin.order.application.OrderService;
import jw.project.baemin.order.domain.Order;
import jw.project.baemin.order.presentation.request.CreateOrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ApiResponse<?> createOrder(@RequestBody CreateOrderRequest request) {
        return ApiResponse.success(orderService.createOrder(request));
    }

    @GetMapping("/customer/{customerId}/all")
    public ApiResponse<?> findCustomerOrders(@PathVariable Long customerId) {
        return ApiResponse.success(orderService.findCustomerOrders(customerId));
    }

    @GetMapping("/restaurant/{restaurantId}/all")
    public ApiResponse<?> findRestaurantOrders(@PathVariable Long restaurantId) {
        return ApiResponse.success(orderService.findRestaurantOrders(restaurantId));
    }

    @GetMapping("/restaurant/{restaurantId}/ordered")
    public ApiResponse<?> findNotAcceptedOrder(@PathVariable Long restaurantId) {
        List<Order> notAcceptedOrders
            = orderService.findNotAcceptedOrders(restaurantId);
        return ApiResponse.success(notAcceptedOrders);
    }

    @PostMapping("/{orderId}/accept")
    public ApiResponse<?> acceptOrder(@PathVariable Long orderId) {
        orderService.acceptOrder(orderId);
        return ApiResponse.success(null);
    }

    @PostMapping("/{orderId}/reject")
    public ApiResponse<?> rejectOrder(@PathVariable Long orderId) {
        orderService.rejectOrder(orderId);
        return ApiResponse.success(null);
    }

}
