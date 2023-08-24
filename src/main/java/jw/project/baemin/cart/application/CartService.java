package jw.project.baemin.cart.application;

import java.util.List;
import java.util.stream.Collectors;
import jw.project.baemin.cart.domain.Cart;
import jw.project.baemin.cart.domain.CartItem;
import jw.project.baemin.cart.domain.CartItemRepository;
import jw.project.baemin.cart.infrastructure.CartRepository;
import jw.project.baemin.cart.presentation.request.AddCartItemToCartRequest;
import jw.project.baemin.cart.presentation.response.CartItemResponse;
import jw.project.baemin.restaurant.menu.application.MenuService;
import jw.project.baemin.restaurant.menu.presentation.response.MenuResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

    private final MenuService menuService;
    private final CartRepository cartRepository;

    private final CartItemRepository cartItemRepository;

    public Long addDeliveryMenuToCart(Long customerId,
        Long restaurantId, AddCartItemToCartRequest request) {
        Cart cart = getOrCreateCart(customerId, restaurantId);
        CartItem cartItem = request.toEntity();

        cart.addCartItem(cartItem);
        return cartRepository.save(cart).getCustomerId();
    }

    public void addMenus(Long customerId,
        Long restaurantId, List<AddCartItemToCartRequest> request) {
        getOrCreateCart(customerId, restaurantId);

        List<CartItem> cartItem = request.stream()
            .map(item -> item.toEntityForJDBC(customerId))
            .collect(Collectors.toList());

        cartItemRepository.saveAll(customerId, cartItem);
    }

    public void deleteCartItem(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    public CartItemResponse findCartItem(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId);

        MenuResponse menu = menuService.findMenu(cartItem.getMenu().getId());

        return CartItemResponse.from(cartItem.getId(), cartItem.getCount(), menu);
    }

    public List<CartItemResponse> findAllCartItems(Long customerId) {
        Cart cart = cartRepository.findById(customerId).orElseThrow(RuntimeException::new);

        List<MenuResponse> menusInMenuIds = menuService.findMenusInMenuIds(customerId);

        return cart.getCartItems()
            .stream()
            .map(cartItem ->
                CartItemResponse.from(
                    cartItem.getId(),
                    cartItem.getCount(),
                    findMenuByMenuIds(menusInMenuIds, cartItem.getMenu().getId())))
            .collect(Collectors.toList());
    }

    public Long updateCartItem(Long cartItemId, Integer count) {
        CartItem cartItem = cartItemRepository.findById(cartItemId);

        cartItem.changeCount(count);
        return cartItemRepository.save(cartItem).getId();
    }

    private Cart getOrCreateCart(Long customerId, Long restaurantId) {
        Cart cart = cartRepository.findById(customerId)
            .orElse(
                Cart.builder()
                    .restaurantId(restaurantId)
                    .customerId(customerId)
                    .build()
            );
        if (!cart.getRestaurantId().equals(restaurantId)) {
            throw new RuntimeException();
        }
        return cart;
    }

    private MenuResponse findMenuByMenuIds(List<MenuResponse> menus, Long menuId) {
        return menus.stream()
            .filter(menu -> menu.menuId().equals(menuId))
            .findFirst()
            .orElseThrow(RuntimeException::new);
    }
}
