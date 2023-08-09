package jw.project.baemin.order.infrastructure;

import java.util.List;
import jw.project.baemin.order.domain.Order;
import jw.project.baemin.order.domain.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findByCustomerId(Long customerId);

    List<Order> findByRestaurantId(Long restaurantId);

    List<Order> findByRestaurantIdAndOrderStatus(Long restaurantId, OrderStatus orderStatus);
}
