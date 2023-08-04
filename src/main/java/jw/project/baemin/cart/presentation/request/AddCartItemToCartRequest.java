package jw.project.baemin.cart.presentation.request;

import java.math.BigDecimal;
import jw.project.baemin.cart.domain.CartItem;

public record AddCartItemToCartRequest(Long menuId, Integer count, BigDecimal price) {
    public CartItem toEntity() {
        return CartItem.builder()
            .count(count)
            .build();
    }
}
