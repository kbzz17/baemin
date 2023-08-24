package jw.project.baemin.cart.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import jw.project.baemin.restaurant.menu.domain.Menu;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Transient
    private Long menuId;
    @Transient
    private Long cartId;

    private Integer count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public void changeCount(Integer updateCount) {
        this.count = updateCount;
    }

    public CartItem() {
    }

    @Builder
    public CartItem(Long id, Menu menu, Long menuId, Long cartId, Integer count, Cart cart) {
        this.id = id;
        this.menu = menu;
        this.menuId = menuId;
        this.cartId = cartId;
        this.count = count;
        this.cart = cart;
    }
}
