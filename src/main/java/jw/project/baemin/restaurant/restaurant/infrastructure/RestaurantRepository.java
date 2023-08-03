package jw.project.baemin.restaurant.restaurant.infrastructure;

import com.querydsl.core.QueryResults;
import java.util.List;
import java.util.Optional;
import jw.project.baemin.region.domain.RegionCode;
import jw.project.baemin.restaurant.delivery.domain.DeliveryRegion;
import jw.project.baemin.restaurant.restaurant.domain.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository{

    List<Restaurant> findByOwnerId(Long ownerId);

    Optional<Restaurant> findByOwnerIdAndId(Long ownerId, Long restaurantId);

    void deleteById(Long restaurantId);

    Restaurant save(Restaurant restaurant);

    Optional<Restaurant> findById(Long restaurantId);

    Page<Restaurant> findByDeliveryRegionsRegionCode(RegionCode regionCode,
        List<DeliveryRegion> deliveryRegions, PageRequest pageRequest);
}
