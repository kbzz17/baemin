package jw.project.baemin.cart.infrastructure;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import jw.project.baemin.cart.domain.CartItem;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JdbcCartRepository {

    private final static String TABLE = "Cart_Item";
    private final JdbcTemplate jdbcTemplate;

    public void saveAll(Long cartId, List<CartItem> cartItems) {
        String sql = String.format(
            "INSERT INTO `%s` (count, cart_id, menu_id) VALUES (?, ?, ?)",
            TABLE);

        jdbcTemplate.batchUpdate(sql, BatchCartItemSetter(cartId, cartItems));
    }

    private BatchPreparedStatementSetter BatchCartItemSetter(Long cartId, List<CartItem> cartList) {
        return new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                CartItem cartItem = cartList.get(i);
                ps.setInt(1, cartItem.getCount());
                ps.setLong(2, cartId);
                ps.setLong(3, cartItem.getMenuId());
            }

            @Override
            public int getBatchSize() {
                return cartList.size();
            }
        };
    }
}
