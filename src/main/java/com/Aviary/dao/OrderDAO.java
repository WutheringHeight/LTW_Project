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

    public List<Order> findAll() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM orders ORDER BY created_at DESC").mapToBean(Order.class).list());
    }

    public List<OrderItem> findItemsByOrderId(int orderId) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM order_item WHERE order_id = :orderId").bind("orderId", orderId).mapToBean(OrderItem.class).list());
    }

    public void updateStatus(int id, String status) {
        jdbi.useHandle(handle ->
                handle.createUpdate("UPDATE orders SET status = :status WHERE id = :id").bind("status", status).bind("id", id).execute());
    }

    public void delete(int id) {
        jdbi.useHandle(handle -> {
            handle.createUpdate("DELETE FROM order_item WHERE order_id = :id").bind("id", id).execute();
            handle.createUpdate("DELETE FROM orders WHERE id = :id").bind("id", id).execute();
        });
    }

    public List<Order> findPage(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM orders ORDER BY created_at DESC LIMIT :limit OFFSET :offset")
                        .bind("limit", pageSize)
                        .bind("offset", offset)
                        .mapToBean(Order.class)
                        .list()
        );
    }

    public int countOrders() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT COUNT(*) FROM orders")
                        .mapTo(Integer.class)
                        .one()
        );
    }

}
