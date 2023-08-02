package jw.project.baemin.cart.presentation.response;

import java.math.BigDecimal;
import jw.project.baemin.restaurant.menu.presentation.response.MenuResponse;

public record CartItemResponse(Integer count, BigDecimal price, MenuResponse menu) {

    public static CartItemResponse from(Integer count, BigDecimal price, MenuResponse menu) {
        return new CartItemResponse(count, price, menu);
    }
}
