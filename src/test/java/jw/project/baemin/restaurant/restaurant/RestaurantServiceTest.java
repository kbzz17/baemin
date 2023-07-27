package jw.project.baemin.restaurant.restaurant;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import jw.project.baemin.restaurant.restaurant.application.RestaurantService;
import jw.project.baemin.restaurant.restaurant.domain.Restaurant;
import jw.project.baemin.restaurant.restaurant.infrastructure.RestaurantRepository;
import jw.project.baemin.restaurant.restaurant.presentation.request.CreateRestaurantRequest;
import jw.project.baemin.restaurant.restaurant.presentation.request.UpdateRestaurantRequest;
import jw.project.baemin.restaurant.restaurant.presentation.response.RestaurantResponse;
import jw.project.baemin.support.IntegrationTestSupport;
import jw.project.baemin.support.RestaurantSupport;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

public class RestaurantServiceTest extends IntegrationTestSupport {

    @Autowired
    RestaurantService restaurantService;

    @MockBean
    RestaurantRepository restaurantRepository;

    @Test
    @DisplayName("식당 등록 기능 테스트")
    void createRestaurantByOwnerId() {
        Restaurant restaurant = RestaurantSupport.get(1L);

        CreateRestaurantRequest createRequest = RestaurantSupport.getCreateRestaurantRequest();

        given(restaurantRepository.save(any())).willReturn(restaurant);

        RestaurantResponse result = restaurantService.createRestaurantByOwnerId(1L,
            createRequest);

        assertThat(result).isEqualTo(RestaurantResponse.from(restaurant));
    }

    @Test
    @DisplayName("restaurantId를 이용한 식당 검색 기능 테스트")
    void findRestaurantByRestaurantId() {
        Restaurant restaurant = RestaurantSupport.get(1L);

        given(restaurantRepository.findById(anyLong())).willReturn(Optional.of(restaurant));

        RestaurantResponse result = restaurantService.findRestaurantByRestaurantId(
            1L);

        assertThat(result).isEqualTo(RestaurantResponse.from(restaurant));
    }

    @Test
    @DisplayName("owner 모든 식당 검색 기능 테스트")
    void findOwnerRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant1 = RestaurantSupport.get(1L);
        Restaurant restaurant2 = RestaurantSupport.get(2L);
        restaurants.add(restaurant1);
        restaurants.add(restaurant2);

        given(restaurantRepository.findByOwnerId(anyLong())).willReturn(restaurants);

        List<RestaurantResponse> ownerRestaurants = restaurantService.findOwnerRestaurants(1L);

        assertThat(ownerRestaurants).contains(RestaurantResponse.from(restaurant1),
            RestaurantResponse.from(restaurant2));
    }

    @Test
    @DisplayName("식당 정보 수정 기능 테스트")
    void updateRestaurant() {

        Restaurant restaurant = RestaurantSupport.get(1L);
        UpdateRestaurantRequest request = RestaurantSupport.getUpdateRestaurantRequest();

        given(restaurantRepository.findByOwnerIdAndRestaurantId(anyLong(), anyLong())).willReturn(
            Optional.of(restaurant));
        restaurant.update(request);
        given(restaurantRepository.save(any())).willReturn(restaurant);

        RestaurantResponse result = restaurantService.updateRestaurant(1L, 1L, request);

        assertThat(result.id()).isEqualTo(1L);
        assertThat(result.openTime()).isEqualTo(LocalTime.NOON);
        assertThat(result.closeTime()).isEqualTo(LocalTime.MIDNIGHT);
        assertThat(result.address()).isEqualTo("서울시 강남구 역삼동");
        assertThat(result.description()).isEqualTo("아이우에오");
    }
}
