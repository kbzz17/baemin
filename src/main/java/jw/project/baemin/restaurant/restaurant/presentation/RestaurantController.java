package jw.project.baemin.restaurant.restaurant.presentation;

import jw.project.baemin.common.ApiResponse;
import jw.project.baemin.restaurant.restaurant.application.RestaurantService;
import jw.project.baemin.restaurant.restaurant.domain.eums.OrderType;
import jw.project.baemin.restaurant.restaurant.domain.eums.SupportPayment;
import jw.project.baemin.restaurant.restaurant.presentation.request.CreateRestaurantRequest;
import jw.project.baemin.restaurant.restaurant.presentation.request.UpdateRestaurantRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping("/owner/{ownerId}")
    public ApiResponse<?> createRestaurantByOwnerId(@PathVariable Long ownerId,
        @RequestBody CreateRestaurantRequest request) {
        return ApiResponse.success(restaurantService.createRestaurantByOwnerId(ownerId, request));
    }

    @GetMapping("/{restaurantId}")
    public ApiResponse<?> findRestaurantByRestaurantId(@PathVariable Long restaurantId) {
        return ApiResponse.success(restaurantService.findRestaurantByRestaurantId(restaurantId));
    }

    @GetMapping("/owner/{ownerId}")
    public ApiResponse<?> findOwnerRestaurants(@PathVariable Long ownerId) {
        return ApiResponse.success(restaurantService.findOwnerRestaurants(ownerId));
    }

    @PutMapping("/owner/{ownerId}/restaurant/{restaurantId}")
    public ApiResponse<?> updateRestaurant(@PathVariable Long ownerId,
        @PathVariable Long restaurantId, @RequestBody UpdateRestaurantRequest request) {
        return ApiResponse.success(
            restaurantService.updateRestaurant(ownerId, restaurantId, request));
    }

    @DeleteMapping("/owner/{ownerId}/restaurant/{restaurantId}")
    public ApiResponse<?> deleteRestaurant(@PathVariable Long ownerId,
        @PathVariable Long restaurantId) {
        restaurantService.deleteRestaurant(ownerId, restaurantId);
        return ApiResponse.success(null);
    }

    @PostMapping("/status/{restaurantId}")
    public ApiResponse<?> changeRestaurantStatus(@PathVariable Long restaurantId) {
        return ApiResponse.success(restaurantService.changeRestaurantStatus(restaurantId));
    }

    @PostMapping("/{restaurantId/supportPayment/{supportPayment}")
    public ApiResponse<?> changeSupportPayment(@PathVariable Long restaurantId, @PathVariable
    SupportPayment supportPayment) {
        return ApiResponse.success(
            restaurantService.changeSupportPayment(restaurantId, supportPayment));
    }

    @PostMapping("/{restaurantId}/orderType/{orderType}")
    public ApiResponse<?> changeOrderType(@PathVariable Long restaurantId,
        @PathVariable OrderType orderType) {

        return ApiResponse.success(restaurantService.changeOrderType(restaurantId, orderType));
    }

}
