package jw.project.baemin.order.infrastructure;

import jw.project.baemin.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {

}
