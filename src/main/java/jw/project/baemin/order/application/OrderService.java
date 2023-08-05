package jw.project.baemin.order.application;

import java.util.List;
import jw.project.baemin.cart.application.CartService;
import jw.project.baemin.cart.domain.CartItem;
import jw.project.baemin.cart.presentation.response.CartItemResponse;
import jw.project.baemin.customer.application.CouponService;
import jw.project.baemin.customer.application.CustomerService;
import jw.project.baemin.customer.domain.Coupon;
import jw.project.baemin.customer.domain.Customer;
import jw.project.baemin.customer.infrastructure.CouponRepository;
import jw.project.baemin.customer.infrastructure.CustomerAddressRepository;
import jw.project.baemin.customer.infrastructure.CustomerRepository;
import jw.project.baemin.delivery.application.DeliveryService;
import jw.project.baemin.delivery.domain.Delivery;
import jw.project.baemin.delivery.infrastructure.DeliveryRepository;
import jw.project.baemin.order.domain.Order;
import jw.project.baemin.order.domain.enums.PaymentType;
import jw.project.baemin.order.infrastructure.OrderRepository;
import jw.project.baemin.order.presentation.request.CreateOrderRequest;
import jw.project.baemin.restaurant.restaurant.domain.Restaurant;
import jw.project.baemin.restaurant.restaurant.infrastructure.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerService customerService;
    private final CouponService couponService;
    private final CartService cartService;

    private final DeliveryService deliveryService;

    private final CustomerAddressRepository customerAddressRepository;
    private final CouponRepository couponRepository;

    private final CustomerRepository customerRepository;

    private final RestaurantRepository restaurantRepository;

    private final OrderRepository orderRepository;

    private final DeliveryRepository deliveryRepository;

    //Customer
    public Long createOrder(CreateOrderRequest request) {
        Customer customer = validCustomerByCustomerId(request.customerId());

        String deliveryAddress = customerAddressRepository.findByCustomerIdAndMainAddressIsTrue(
            request.customerId()).getFullAddress();

        Coupon coupon = validCouponByCouponId(request.couponId());

        Restaurant restaurant = validRestaurantByRestaurantId(request.restaurantId());

        List<CartItemResponse> cartItems = cartService.findAllCartItems(request.customerId());
        List<CartItem> orderItems = cartItems.stream()
            .map(CartItemResponse::toCartItem)
            .toList();

        Order order = Order.createOrder(customer, restaurant, coupon, orderItems,
            request.requests(), PaymentType.valueOf(request.paymentType()),
            request.deliveryFee(), deliveryAddress);

        return orderRepository.save(order).getId();
    }

    //Owner
    public void acceptOrder(Long orderId) {
        Order order = validOrderByOrderId(orderId);
        Delivery delivery = deliveryService.saveDelivery(order);

        order.acceptOrder(delivery);
    }

    public void cancelOrder(Long orderId) {
        Order order = validOrderByOrderId(orderId);
        order.cancelOrder();
        cancelCoupon(order);
    }

    private void cancelCoupon(Order order){
        couponService.cancelCoupon(order.getCoupon().getId());
    }
    private Coupon validCouponByCouponId(Long couponId) {
        return couponRepository.findById(couponId).orElseThrow(RuntimeException::new);
    }

    private Customer validCustomerByCustomerId(Long customerId) {
        return customerRepository.findById(customerId).orElseThrow(RuntimeException::new);
    }

    private Restaurant validRestaurantByRestaurantId(Long restaurantId) {
        return restaurantRepository.findById(restaurantId).orElseThrow(RuntimeException::new);
    }

    private Order validOrderByOrderId(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(RuntimeException::new);
    }

}
