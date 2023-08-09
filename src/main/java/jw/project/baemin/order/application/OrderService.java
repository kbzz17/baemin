package jw.project.baemin.order.application;

import java.util.List;
import jw.project.baemin.customer.application.CouponService;
import jw.project.baemin.customer.application.CustomerAddressService;
import jw.project.baemin.customer.application.CustomerService;
import jw.project.baemin.customer.domain.Coupon;
import jw.project.baemin.customer.domain.Customer;
import jw.project.baemin.delivery.application.DeliveryService;
import jw.project.baemin.delivery.domain.Delivery;
import jw.project.baemin.order.domain.Order;
import jw.project.baemin.order.domain.enums.OrderStatus;
import jw.project.baemin.order.domain.enums.PaymentType;
import jw.project.baemin.order.infrastructure.OrderRepository;
import jw.project.baemin.order.presentation.request.CreateOrderRequest;
import jw.project.baemin.restaurant.restaurant.application.RestaurantService;
import jw.project.baemin.restaurant.restaurant.domain.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerService customerService;

    private final CouponService couponService;

    private final DeliveryService deliveryService;

    private final CustomerAddressService customerAddressService;

    private final RestaurantService restaurantService;

    private final OrderRepository orderRepository;

    //Customer
    public Long createOrder(CreateOrderRequest request) {
        Customer customer = findCustomer(request.customerId());
        String deliveryAddress = getCustomerMainAddress(request.customerId());
        Coupon coupon = useCoupon(request.couponId());
        Restaurant restaurant = validRestaurantByRestaurantId(request.restaurantId());
        PaymentType paymentType = PaymentType.valueOf(request.paymentType());

        Order order = Order.createOrder(customer, restaurant, coupon, request.items(),
            request.requests(), paymentType,
            request.deliveryFee(), deliveryAddress);

        return orderRepository.save(order).getId();
    }

    public Order findOrder(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(RuntimeException::new);
    }

    public List<Order> findCustomerOrders(Long customerId){
        return orderRepository.findByCustomerId(customerId);
    }

    //restaurant
    public void acceptOrder(Long orderId) {
        Order order = validOrderByOrderId(orderId);
        Delivery delivery = deliveryService.saveDelivery(order);

        order.acceptOrder(delivery);
    }

    public void rejectOrder(Long orderId) {
        Order order = validOrderByOrderId(orderId);
        order.cancelOrder();
        cancelCoupon(order);
    }

    public List<Order> findRestaurantOrders(Long restaurantId) {
        return orderRepository.findByRestaurantId(restaurantId);
    }

    public List<Order> findNotAcceptedOrders(Long restaurantId){
        return orderRepository.findByRestaurantIdAndOrderStatus(restaurantId, OrderStatus.ORDERED);
    }

    private Coupon useCoupon(Long couponId) {
        Coupon coupon = couponService.findCouponEntity(couponId);
        coupon.used();
        return coupon;
    }

    private String getCustomerMainAddress(Long customerId) {
        return customerAddressService.findCustomerAddressByIsMainEntity(
            customerId).getFullAddress();
    }

    private Customer findCustomer(Long customerId) {
        return customerService.findCustomer(customerId);
    }


    private void cancelCoupon(Order order) {
        couponService.cancelCoupon(order.getCoupon().getId());
    }

    private Restaurant validRestaurantByRestaurantId(Long restaurantId) {
        return restaurantService.findRestaurantEntityByRestaurantId(restaurantId);
    }

    private Order validOrderByOrderId(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(RuntimeException::new);
    }

}
