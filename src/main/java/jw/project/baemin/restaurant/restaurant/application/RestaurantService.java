package jw.project.baemin.restaurant.restaurant.application;
import java.util.List;
import java.util.stream.Collectors;
import jw.project.baemin.region.application.RegionService;
import jw.project.baemin.region.domain.RegionCode;
import jw.project.baemin.restaurant.category.application.CategoryService;
import jw.project.baemin.restaurant.delivery.domain.DeliveryRegion;
import jw.project.baemin.restaurant.delivery.infrastructure.DeliveryRegionRepository;
import jw.project.baemin.restaurant.restaurant.domain.Restaurant;
import jw.project.baemin.restaurant.restaurant.domain.eums.OrderType;
import jw.project.baemin.restaurant.restaurant.domain.eums.SupportPayment;
import jw.project.baemin.restaurant.restaurant.infrastructure.RestaurantRepository;
import jw.project.baemin.restaurant.restaurant.presentation.request.AddDeliveryRegionRequest;
import jw.project.baemin.restaurant.restaurant.presentation.request.CreateRestaurantRequest;
import jw.project.baemin.restaurant.restaurant.presentation.request.UpdateRestaurantRequest;
import jw.project.baemin.restaurant.restaurant.presentation.response.RestaurantResponse;
import jw.project.baemin.restaurant.restaurantCategory.domain.RestaurantCategory;
import jw.project.baemin.restaurant.restaurantCategory.infrastructure.RestaurantCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    private final RestaurantCategoryRepository restaurantCategoryRepository;

    private final CategoryService categoryService;

    private final RegionService regionService;

    private final DeliveryRegionRepository deliveryRegionRepository;

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

    public Long addRestaurantCategory(Long restaurantId, Long categoryId) {
        Restaurant restaurant = validFindRestaurantById(restaurantId);

        RestaurantCategory restaurantCategory = new RestaurantCategory(restaurant, categoryId);
        restaurant.addCategory(restaurantCategory);
        return restaurantRepository.save(restaurant).getId();
    }

    public Long deleteRestaurantCategory(Long restaurantId, Long categoryId) {
        return restaurantCategoryRepository.deleteByRestaurantIdAndCategoryId(restaurantId,
            categoryId);
    }

    public Long addDeliveryRegion(AddDeliveryRegionRequest request) {
        Restaurant restaurant = validFindRestaurantById(request.restaurantId());
        RegionCode regionCode = regionService.findRegionById(request.regionCodeId());

        DeliveryRegion deliveryRegion = DeliveryRegion.create(regionCode, restaurant,
            request.deliveryFee());

        restaurant.addDeliveryRegion(deliveryRegion);
        return restaurantRepository.save(restaurant).getId();
    }

    public List<RestaurantResponse> findRestaurantByRegionCode(Long regionCodeId,
        PageRequest pageRequest) {
        RegionCode regionCode = regionService.findRegionById(regionCodeId);

        List<DeliveryRegion> deliveryRegions =
            deliveryRegionRepository.findByRegionCodeId(regionCodeId);

        Page<Restaurant> restaurants = restaurantRepository.findByDeliveryRegionsRegionCode(
            regionCode, deliveryRegions, pageRequest);

        return restaurants.stream()
            .map(RestaurantResponse::from)
            .collect(Collectors.toList());
    }


    private Restaurant validFindRestaurantById(Long restaurantId) {
        return restaurantRepository.findById(restaurantId).orElseThrow(RuntimeException::new);
    }
}
