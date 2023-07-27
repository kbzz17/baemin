package jw.project.baemin.restaurant.restaurant.infrastructure;

import java.util.List;
import java.util.Optional;
import jw.project.baemin.restaurant.restaurant.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    List<Restaurant> findByOwnerId(Long ownerId);

    Optional<Restaurant> findByOwnerIdAndRestaurantId(Long ownerId, Long restaurantId);
}
