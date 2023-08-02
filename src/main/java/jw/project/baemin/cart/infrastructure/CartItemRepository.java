package jw.project.baemin.cart.infrastructure;

import jw.project.baemin.cart.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
