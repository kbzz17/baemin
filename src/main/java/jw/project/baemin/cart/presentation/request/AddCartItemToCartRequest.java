package jw.project.baemin.cart.presentation.request;

import java.math.BigDecimal;
import jw.project.baemin.cart.domain.CartItem;

public record AddCartItemToCartRequest(Long menuId, Integer count, BigDecimal price) {
    public CartItem toEntity() {
        return CartItem.builder()
            .count(count)
            .build();
    }

    public CartItem toEntityForJDBC(Long cartId) {
        return CartItem.builder()
            .menuId(menuId)
            .cartId(cartId)
            .count(count)
            .build();
    }
}
