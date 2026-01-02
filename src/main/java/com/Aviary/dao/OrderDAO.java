package com.Aviary.dao;

import com.Aviary.components.CartItem;
import com.Aviary.components.JDBIProvider;
import com.Aviary.components.Order;
import com.Aviary.components.OrderItem;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

public class OrderDAO {

    private final Jdbi jdbi = JDBIProvider.get();

    public int insertOrder(Order order) {
        return jdbi.withHandle(handle ->
                handle.createUpdate("INSERT INTO orders (user_id, customer_name, phone, address, total_price, status) VALUES (:userId, :customerName, :phone, :address, :totalPrice, :status)")
                        .bindBean(order)
                        .executeAndReturnGeneratedKeys("id")
                        .mapTo(int.class)
                        .one()
        );
    }

    public void insertOrderItems(int orderId, List<OrderItem> items) {
        jdbi.useHandle(handle -> {
            for (OrderItem item : items) {
                handle.createUpdate("INSERT INTO order_item  (order_id, product_id, product_name, price, quantity) VALUES (:orderId, :productId, :productName, :price, :quantity)")
                        .bind("orderId", orderId)
                        .bindBean(item)
                        .execute();
            }
        });
    }
}
