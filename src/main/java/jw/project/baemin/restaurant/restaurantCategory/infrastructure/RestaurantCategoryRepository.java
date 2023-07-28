package jw.project.baemin.restaurant.restaurantCategory.infrastructure;

import jw.project.baemin.restaurant.restaurantCategory.domain.RestaurantCategory;
import jw.project.baemin.restaurant.restaurantCategory.domain.RestaurantCategoryId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantCategoryRepository extends JpaRepository<RestaurantCategory, RestaurantCategoryId> {

    Long deleteByRestaurantIdAndCategoryId(Long restaurantId, Long categoryId);
}
