package jw.project.baemin.order;


import static jw.project.baemin.order.domain.enums.PaymentType.MOBILE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.util.List;
import java.util.Optional;
import jw.project.baemin.customer.application.CouponService;
import jw.project.baemin.customer.application.CustomerAddressService;
import jw.project.baemin.customer.application.CustomerService;
import jw.project.baemin.customer.domain.Coupon;
import jw.project.baemin.customer.domain.Customer;
import jw.project.baemin.customer.domain.CustomerAddress;
import jw.project.baemin.customer.infrastructure.CustomerAddressRepository;
import jw.project.baemin.order.application.OrderService;
import jw.project.baemin.order.domain.Order;
import jw.project.baemin.order.domain.OrderItem;
import jw.project.baemin.order.infrastructure.OrderRepository;
import jw.project.baemin.order.presentation.request.CreateOrderRequest;
import jw.project.baemin.restaurant.menu.domain.Menu;
import jw.project.baemin.restaurant.restaurant.application.RestaurantService;
import jw.project.baemin.restaurant.restaurant.domain.Restaurant;
import jw.project.baemin.restaurant.restaurant.infrastructure.RestaurantRepository;
import jw.project.baemin.support.CouponSupport;
import jw.project.baemin.support.CustomerAddressSupport;
import jw.project.baemin.support.CustomerSupport;
import jw.project.baemin.support.IntegrationTestSupport;
import jw.project.baemin.support.MenuSupport;
import jw.project.baemin.support.OrderSupport;
import jw.project.baemin.support.RestaurantSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

public class OrderServiceTest extends IntegrationTestSupport {

    @MockBean
    CustomerService customerService;

    @MockBean
    CustomerAddressService customerAddressService;

    @MockBean
    CouponService couponService;

    @MockBean
    RestaurantService restaurantService;

    @MockBean
    CustomerAddressRepository customerAddressRepository;
    @Autowired
    OrderService orderService;

    @MockBean
    RestaurantRepository restaurantRepository;

    @MockBean
    OrderRepository orderRepository;

    Customer customer;
    Long customerId;
    CustomerAddress customerAddress;
    Coupon coupon;
    Restaurant restaurant;
    Menu menu;
    Order order;

    List<OrderItem> orderItem;

    @BeforeEach
    void init() {
        customer = CustomerSupport.get(1L);
        customerId = customer.getId();
        customerAddress = CustomerAddressSupport.get(customerId);
        coupon = CouponSupport.get(1L);
        restaurant = RestaurantSupport.get(1L);
        menu = MenuSupport.get(1L);

        OrderItem item = OrderItem.builder()
            .id(1L)
            .price(1000)
            .count(3)
            .menu(menu)
            .build();

        orderItem = List.of(item);

        order = OrderSupport.get(customer, restaurant, coupon, orderItem);

    }

    @Test
    void createOrderTest() {
        given(customerService.findCustomer(customerId)).willReturn(customer);
        given(customerAddressService.findCustomerAddressByIsMainEntity(any(Long.class)))
            .willReturn(customerAddress);
        given(couponService.findCouponEntity(1L)).willReturn(coupon);

        given(restaurantService.findRestaurantEntityByRestaurantId(1L)).willReturn(
            restaurant);

        given(orderRepository.save(any())).willReturn(order);
        given(orderRepository.findById(1L)).willReturn(Optional.of(order));

        CreateOrderRequest request = new CreateOrderRequest(1L, 1L, 1L, "abcd",
            MOBILE.toString(), 1000, orderItem);

        Long result = orderService.createOrder(request);

        Order findOrder = orderService.findOrder(1L);
        assertEquals(findOrder.getFinalPrice(), 1000);
    }
}
