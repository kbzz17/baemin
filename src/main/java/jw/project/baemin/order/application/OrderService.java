package jw.project.baemin.order.application;

import jw.project.baemin.customer.application.CouponService;
import jw.project.baemin.customer.application.CustomerAddressService;
import jw.project.baemin.customer.application.CustomerService;
import jw.project.baemin.customer.domain.Coupon;
import jw.project.baemin.customer.domain.Customer;
import jw.project.baemin.customer.infrastructure.CouponRepository;
import jw.project.baemin.customer.infrastructure.CustomerAddressRepository;
import jw.project.baemin.customer.infrastructure.CustomerRepository;
import jw.project.baemin.delivery.application.DeliveryService;
import jw.project.baemin.delivery.domain.Delivery;
import jw.project.baemin.order.domain.Order;
import jw.project.baemin.order.domain.enums.PaymentType;
import jw.project.baemin.order.infrastructure.OrderRepository;
import jw.project.baemin.order.presentation.request.CreateOrderRequest;
import jw.project.baemin.payment.domain.Payment;
import jw.project.baemin.restaurant.restaurant.domain.Restaurant;
import jw.project.baemin.restaurant.restaurant.infrastructure.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerService customerService;

    private final CouponService couponService;

    private final DeliveryService deliveryService;

    private final CustomerAddressService customerAddressService;

    private final RestaurantRepository restaurantRepository;

    private final OrderRepository orderRepository;

    //Customer
    public Long createOrder(CreateOrderRequest request) {
        Customer customer = customerService.findCustomer(request.customerId());

        String deliveryAddress = customerAddressService.findCustomerAddressByIsMainEntity(
            request.customerId()).getFullAddress();

        Coupon coupon = couponService.findCouponEntity(request.couponId());
        coupon.used();

        Restaurant restaurant = validRestaurantByRestaurantId(request.restaurantId());

        Order order = Order.createOrder(customer, restaurant, coupon, request.items(),
            request.requests(), PaymentType.valueOf(request.paymentType()),
            request.deliveryFee(), deliveryAddress);

        Payment payment = Payment.createPayment(customer, order,
            PaymentType.valueOf(request.paymentType()), order.getFinalPrice());

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

    private void cancelCoupon(Order order) {
        couponService.cancelCoupon(order.getCoupon().getId());
    }


    private Restaurant validRestaurantByRestaurantId(Long restaurantId) {
        return restaurantRepository.findById(restaurantId).orElseThrow(RuntimeException::new);
    }

    private Order validOrderByOrderId(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(RuntimeException::new);
    }

}
