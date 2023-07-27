package jw.project.baemin.restaurant.restaurant.application;

import java.util.List;
import java.util.stream.Collectors;
import jw.project.baemin.restaurant.restaurant.domain.Restaurant;
import jw.project.baemin.restaurant.restaurant.domain.eums.OrderType;
import jw.project.baemin.restaurant.restaurant.domain.eums.SupportPayment;
import jw.project.baemin.restaurant.restaurant.infrastructure.RestaurantRepository;
import jw.project.baemin.restaurant.restaurant.presentation.request.CreateRestaurantRequest;
import jw.project.baemin.restaurant.restaurant.presentation.request.UpdateRestaurantRequest;
import jw.project.baemin.restaurant.restaurant.presentation.response.RestaurantResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantResponse createRestaurantByOwnerId(Long ownerId,
        CreateRestaurantRequest request) {
        Restaurant restaurant = request.toEntity();
        return RestaurantResponse.from(restaurantRepository.save(restaurant));
    }

    public RestaurantResponse findRestaurantByRestaurantId(Long restaurantId) {
        Restaurant restaurant = validFindRestaurantById(restaurantId);
        return RestaurantResponse.from(restaurant);
    }

    public List<RestaurantResponse> findOwnerRestaurants(Long ownerId) {
        List<Restaurant> restaurants = restaurantRepository.findByOwnerId(ownerId);
        return restaurants.stream()
            .map(RestaurantResponse::from)
            .collect(Collectors.toList());
    }

    public RestaurantResponse updateRestaurant(Long ownerId, Long restaurantId,
        UpdateRestaurantRequest request) {
        Restaurant restaurant = validFindRestaurantById(restaurantId);
        restaurant.update(request);
        return RestaurantResponse.from(restaurantRepository.save(restaurant));
    }

    public void deleteRestaurant(Long ownerId, Long restaurantId) {
        restaurantRepository.deleteById(restaurantId);
    }

    public Long changeRestaurantStatus(Long restaurantId) {
        Restaurant restaurant = validFindRestaurantById(restaurantId);
        restaurant.changeStatus();
        return restaurantRepository.save(restaurant).getId();
    }

    public Long changeOrderType(Long restaurantId, OrderType orderType) {
        Restaurant restaurant = validFindRestaurantById(restaurantId);
        restaurant.changeOrderType(orderType);
        return restaurantRepository.save(restaurant).getId();
    }

    public Long changeSupportPayment(Long restaurantId, SupportPayment supportPayment) {
        Restaurant restaurant = validFindRestaurantById(restaurantId);
        restaurant.changeSupportPayment(supportPayment);
        return restaurantRepository.save(restaurant).getId();
    }

    private Restaurant validFindRestaurantById(Long restaurantId) {
        return restaurantRepository.findById(restaurantId).orElseThrow(RuntimeException::new);
    }
}
