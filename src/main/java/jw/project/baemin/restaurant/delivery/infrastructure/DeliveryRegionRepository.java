package jw.project.baemin.restaurant.delivery.infrastructure;

import java.util.List;
import jw.project.baemin.restaurant.delivery.domain.DeliveryRegion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRegionRepository extends JpaRepository<DeliveryRegion, Long> {

    List<DeliveryRegion> findByRegionCodeId(Long regionCodeId);
}
