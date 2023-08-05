package jw.project.baemin.cart.presentation.response;

import jw.project.baemin.cart.domain.CartItem;
import jw.project.baemin.restaurant.menu.presentation.response.MenuResponse;

public record CartItemResponse(Long cartItemId, Integer count, MenuResponse menu) {

    public static CartItemResponse from(Long cartItemId, Integer count, MenuResponse menu) {
        return new CartItemResponse(cartItemId, count, menu);
    }

    public CartItem toCartItem(){
        return CartItem.builder()
            .id(cartItemId)
            .count(count)
            .build();
    }

}
