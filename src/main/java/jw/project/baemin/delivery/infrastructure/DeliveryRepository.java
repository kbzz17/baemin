package jw.project.baemin.delivery.infrastructure;

import jw.project.baemin.delivery.domain.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

}
