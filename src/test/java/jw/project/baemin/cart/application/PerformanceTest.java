package jw.project.baemin.cart.application;

import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.List;
import jw.project.baemin.cart.domain.Cart;
import jw.project.baemin.cart.domain.CartItem;
import jw.project.baemin.cart.domain.CartItemRepository;
import jw.project.baemin.cart.infrastructure.CartRepository;
import jw.project.baemin.cart.infrastructure.JpaCartItemRepository;
import jw.project.baemin.restaurant.menu.domain.Menu;
import jw.project.baemin.restaurant.menu.infrastructure.MenuRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

@SpringBootTest
public class PerformanceTest {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    MenuRepository menuRepository;
    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    JpaCartItemRepository jpaCartItemRepository;

    List<CartItem> cartItems;

    @BeforeEach
    void init() {
        Faker faker = new Faker();
        cartItems = new ArrayList<>();
        Cart cart = cartRepository.findById(1L).orElseThrow(RuntimeException::new);

        for (int i = 1; i < 100000; i++) {
            Menu menu = menuRepository.findById((long) (i)).orElseThrow();
            CartItem cartItem = CartItem.builder()
                .cart(cart)
                .menu(menu)
                .count(faker.number().numberBetween(1, 10))
                .build();

            cartItems.add(cartItem);
        }
    }

    @Test
    void Generate_CartItem_By_JDBC() {
        Long cartId = 1L;
        StopWatch stopWatch = new StopWatch();

        stopWatch.start();
        cartItemRepository.saveAll(cartId, cartItems);
        stopWatch.stop();

        System.out.println("------------Test result------------");
        System.out.println(stopWatch.getTotalTimeSeconds());
        System.out.println("-----------------------------------");
    }

    @Test
    void Generate_CartItem_By_JPA() {
        StopWatch stopWatch = new StopWatch();

        stopWatch.start();
        jpaCartItemRepository.saveAll(cartItems);
        stopWatch.stop();

        System.out.println("------------Test result------------");
        System.out.println(stopWatch.getTotalTimeSeconds());
        System.out.println("-----------------------------------");
    }
}
