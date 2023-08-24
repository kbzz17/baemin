package jw.project.baemin.cart.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.math.BigDecimal;
import java.util.Optional;
import jw.project.baemin.cart.domain.Cart;
import jw.project.baemin.cart.domain.CartItem;
import jw.project.baemin.cart.infrastructure.JpaCartItemRepository;
import jw.project.baemin.cart.infrastructure.CartRepository;
import jw.project.baemin.cart.presentation.request.AddCartItemToCartRequest;
import jw.project.baemin.support.IntegrationTestSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

public class CartServiceTest extends IntegrationTestSupport {

    @MockBean
    JpaCartItemRepository jpaCartItemRepository;

    @MockBean
    CartRepository cartRepository;

    @Autowired
    CartService cartService;

    @Test
    @DisplayName("장바구니 메뉴 등록 테스트")
    void addDeliveryMenuToCart() {
        Cart cart = Cart.builder()
            .customerId(3L)
            .restaurantId(1L)
            .build();
        AddCartItemToCartRequest request = new AddCartItemToCartRequest(1L, 10,
            new BigDecimal(100));
        CartItem cartItem = request.toEntity();
        cart.addCartItem(cartItem);

        given(cartRepository.save(any())).willAnswer(invocation -> invocation.getArgument(0));

        Long result = cartService.addDeliveryMenuToCart(3L, 1L, request);

        assertThat(result).isEqualTo(cart.getCustomerId());
    }

    @Test
    @DisplayName("장바구니 메뉴 등록 시 상이한 restaurant 음식 추가 테스트")
    void AddDifferentRestaurantMenuToCart() {
        Cart cart = Cart.builder()
            .customerId(3L)
            .restaurantId(1L)
            .build();
        AddCartItemToCartRequest request = new AddCartItemToCartRequest(1L, 10,
            new BigDecimal(100));
        CartItem cartItem = request.toEntity();
        cart.addCartItem(cartItem);

        given(cartRepository.findById(3L)).willReturn(Optional.of(cart));
        given(cartRepository.save(any())).willAnswer(invocation -> invocation.getArgument(0));

//        Long result = cartService.addDeliveryMenuToCart(3L, 1L, request);

        assertThatThrownBy(() -> {
            cartService.addDeliveryMenuToCart(3L, 2L, request);
        })
            .isInstanceOf(RuntimeException.class);
    }
}
