package jw.project.baemin.restaurant.restaurantCategory.domain;

import java.io.Serializable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RestaurantCategoryId implements Serializable {
    private Long restaurant;

    private Long categoryId;
}
