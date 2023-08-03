package jw.project.baemin.restaurant.restaurant.infrastructure;

import static jw.project.baemin.restaurant.restaurant.domain.QRestaurant.*;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import jw.project.baemin.region.domain.RegionCode;
import jw.project.baemin.restaurant.delivery.domain.DeliveryRegion;
import jw.project.baemin.restaurant.restaurant.domain.QRestaurant;
import jw.project.baemin.restaurant.restaurant.domain.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RestaurantRepositoryAdapter implements RestaurantRepository {

    private final JpaRestaurantRepository jpaRestaurantRepository;
    private final JPAQueryFactory queryFactory;

    @Override
    public void deleteById(Long restaurantId) {
        jpaRestaurantRepository.deleteById(restaurantId);
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        return jpaRestaurantRepository.save(restaurant);
    }

    @Override
    public Optional<Restaurant> findById(Long restaurantId) {
        return jpaRestaurantRepository.findById(restaurantId);
    }

    @Override
    public List<Restaurant> findByOwnerId(Long ownerId) {
        return jpaRestaurantRepository.findByOwnerId(ownerId);
    }

    @Override
    public Optional<Restaurant> findByOwnerIdAndId(Long ownerId, Long restaurantId) {
        return jpaRestaurantRepository.findByOwnerIdAndId(ownerId, restaurantId);
    }


    @Override
    public Page<Restaurant> findByDeliveryRegionsRegionCode(RegionCode regionCode,
        List<DeliveryRegion> deliveryRegions, PageRequest pageRequest) {
        QRestaurant Qrestaurant = restaurant;

        BooleanExpression containsDeliveryRegion =
            Qrestaurant.deliveryRegions.any().in(deliveryRegions);

        List<Restaurant> restaurants = queryFactory.selectFrom(Qrestaurant)
            .where(containsDeliveryRegion)
            .offset(pageRequest.getOffset())
            .limit(pageRequest.getPageSize())
            .fetch();

        JPAQuery<Long> count = queryFactory.select(Qrestaurant.count())
            .from(Qrestaurant)
            .where(containsDeliveryRegion);

        return PageableExecutionUtils.getPage(restaurants, pageRequest, count::fetchOne);
    }
}
