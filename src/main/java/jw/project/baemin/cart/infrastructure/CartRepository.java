package jw.project.baemin.cart.infrastructure;

import jw.project.baemin.cart.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
