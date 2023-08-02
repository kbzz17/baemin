package jw.project.baemin.cart.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class Cart {

    @Id
    private Long customerId;

    private Long restaurantId;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();

    public void addCartItem(CartItem cartItem) {
        if(this.cartItems == null){
            this.cartItems = new ArrayList<>();
        }
        this.cartItems.add(cartItem);
    }

    public Cart() {
    }

    @Builder
    public Cart(Long customerId, Long restaurantId, List<CartItem> cartItems) {
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.cartItems = cartItems;
    }
}
