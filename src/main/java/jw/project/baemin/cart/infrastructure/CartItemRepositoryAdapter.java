package jw.project.baemin.cart.infrastructure;

import java.util.List;
import jw.project.baemin.cart.domain.Cart;
import jw.project.baemin.cart.domain.CartItem;
import jw.project.baemin.cart.domain.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CartItemRepositoryAdapter implements CartItemRepository {

    private final JpaCartItemRepository jpaCartItemRepository;

    private final JdbcCartRepository jdbcCartRepository;

    @Override
    public CartItem findById(Long id) {
        return jpaCartItemRepository.findById(id).orElseThrow();
    }

    @Override
    public List<CartItem> findByCart(Cart cart) {
        return jpaCartItemRepository.findByCart(cart);
    }

    @Override
    public void deleteById(Long id) {
        jpaCartItemRepository.deleteById(id);
    }

    @Override
    public CartItem save(CartItem cartItem) {
        return jpaCartItemRepository.save(cartItem);
    }

    @Override
    public void saveAll(Long cartId, List<CartItem> cartItems) {
        jdbcCartRepository.saveAll(cartId, cartItems);
    }
}
