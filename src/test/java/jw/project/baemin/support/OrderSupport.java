package jw.project.baemin.support;

import static jw.project.baemin.order.domain.enums.OrderStatus.ORDERED;

import java.util.List;
import jw.project.baemin.customer.domain.Coupon;
import jw.project.baemin.customer.domain.Customer;
import jw.project.baemin.order.domain.Order;
import jw.project.baemin.order.domain.OrderItem;
import jw.project.baemin.order.domain.enums.PaymentType;
import jw.project.baemin.restaurant.restaurant.domain.Restaurant;

public class OrderSupport {
    private static final String requests = "무 많이요";
    private static final PaymentType paymentType = PaymentType.MOBILE;
    private static final Integer deliveryFee = 1000;
    private static final String deliveryAddress = "서울시 성동구 길음동 어쩌구아파트 101호";

    public static Order get(Customer customer, Restaurant restaurant, Coupon coupon,
        List<OrderItem> orderItems) {

        int orderPrice = orderItems.stream()
            .mapToInt(item -> item.getMenu().getPrice() * item.getCount())
            .sum();

        int finalPrice = coupon.calculateDiscount(orderPrice + deliveryFee);

        return Order.builder()
            .customer(customer)
            .restaurant(restaurant)
            .orderItems(orderItems)
            .coupon(coupon)
            .requests(requests)
            .orderPrice(orderPrice)
            .deliveryFee(deliveryFee)
            .discountPrice(orderPrice + deliveryFee - finalPrice)
            .finalPrice(finalPrice)
            .deliveryAddress(deliveryAddress)
            .paymentType(paymentType)
            .orderStatus(ORDERED)
            .build();
    }

}
