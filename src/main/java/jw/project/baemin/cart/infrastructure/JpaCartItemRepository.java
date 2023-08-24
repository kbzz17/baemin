package jw.project.baemin.cart.infrastructure;

import java.util.List;
import jw.project.baemin.cart.domain.Cart;
import jw.project.baemin.cart.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCartItemRepository extends JpaRepository<CartItem, Long> {
     List<CartItem> findByCart(Cart cart);
}
