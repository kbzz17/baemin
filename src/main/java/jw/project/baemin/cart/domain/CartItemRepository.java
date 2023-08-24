package jw.project.baemin.cart.domain;

import java.util.List;

public interface CartItemRepository {
    CartItem findById(Long id);

    List<CartItem> findByCart(Cart cart);

    void deleteById(Long id);

    CartItem save(CartItem cartItem);

    void saveAll(Long cartId, List<CartItem> cartItems);
}
