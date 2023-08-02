package jw.project.baemin.cart.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long menuId;

    private Integer count;

    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public void changeCount(Integer updateCount) {
        this.count = updateCount;
    }

    public CartItem() {
    }

    @Builder
    public CartItem(Long id, Long menuId, Integer count, BigDecimal price) {
        this.id = id;
        this.menuId = menuId;
        this.count = count;
        this.price = price;
    }
}
