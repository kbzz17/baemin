package jw.project.baemin.cart.presentation;

import jw.project.baemin.cart.application.CartService;
import jw.project.baemin.cart.presentation.request.AddCartItemToCartRequest;
import jw.project.baemin.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartService cartService;

    @PostMapping("/{customerId}/restaurant/{restaurantId}")
    public ApiResponse<?> addDeliveryMenuToCart(@PathVariable Long customerId,
        @PathVariable Long restaurantId, @RequestBody AddCartItemToCartRequest request) {
        return ApiResponse.success(
            cartService.addDeliveryMenuToCart(customerId, restaurantId, request));
    }

    @GetMapping("/{customerId}")
    public ApiResponse<?> findAllCartItems(@PathVariable Long customerId) {
        return ApiResponse.success(cartService.findAllCartItems(customerId));
    }

    @DeleteMapping("/{cartItemId}")
    public ApiResponse<?> deleteCartItem(@PathVariable Long cartItemId) {
        cartService.deleteCartItem(cartItemId);
        return ApiResponse.success(null);
    }
}
