package jw.project.baemin.cart.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jw.project.baemin.order.domain.Order;
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

    private Integer count;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public void changeCount(Integer updateCount) {
        this.count = updateCount;
    }

    public CartItem() {
    }

    @Builder
    public CartItem(Long id, Menu menu, Integer count) {
        this.id = id;
        this.menu = menu;
        this.count = count;
    }
}