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
    public List<Order> findPage( int page, int pageSize, String status, String fromDate, String toDate,String keyword ) {
        int offset = (page - 1) * pageSize;

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM orders WHERE 1=1");

        if (status != null && !status.isEmpty()) {
            sql.append(" AND status = :status");
        }

        if (fromDate != null && !fromDate.isEmpty()) {
            sql.append(" AND updated_at >= :fromDate");
        }

        if (toDate != null && !toDate.isEmpty()) {
            sql.append(" AND updated_at <= :toDate");
        }
        if (keyword != null && !keyword.isEmpty()) {
            sql.append(" AND ( CAST(id AS CHAR) LIKE :keyword OR customer_name LIKE :keyword OR phone LIKE :keyword ) ");
        }
        sql.append(" ORDER BY updated_at DESC LIMIT :limit OFFSET :offset");

        return jdbi.withHandle(handle -> {
            org.jdbi.v3.core.statement.Query query =
                    handle.createQuery(sql.toString());

            query.bind("limit", pageSize);
            query.bind("offset", offset);

            if (status != null && !status.isEmpty()) {
                query.bind("status", status);
            }

            if (fromDate != null && !fromDate.isEmpty()) {
                query.bind("fromDate", fromDate);
            }

            if (toDate != null && !toDate.isEmpty()) {
                query.bind("toDate", toDate);
            }
            if (keyword != null && !keyword.isEmpty()) {
                query.bind("keyword", "%" + keyword + "%");
            }

            return query.mapToBean(Order.class).list();
        });
    }
    public int countOrders(String status, String fromDate, String toDate,String keyword) {

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(*) FROM orders WHERE 1=1");

        if (status != null && !status.isEmpty()) {
            sql.append(" AND status = :status");
        }

        if (fromDate != null && !fromDate.isEmpty()) {
            sql.append(" AND updated_at >= :fromDate");
        }

        if (toDate != null && !toDate.isEmpty()) {
            sql.append(" AND updated_at <= :toDate");
        }
        if (keyword != null && !keyword.isEmpty()) {
            sql.append(" AND ( CAST(id AS CHAR) LIKE :keyword OR customer_name LIKE :keyword OR phone LIKE :keyword ) ");
        }

        return jdbi.withHandle(handle -> {
            org.jdbi.v3.core.statement.Query query =
                    handle.createQuery(sql.toString());

            if (status != null && !status.isEmpty()) {
                query.bind("status", status);
            }

            if (fromDate != null && !fromDate.isEmpty()) {
                query.bind("fromDate", fromDate);
            }

            if (toDate != null && !toDate.isEmpty()) {
                query.bind("toDate", toDate);
            }
            if (keyword != null && !keyword.isEmpty()) {
                query.bind("keyword", "%" + keyword + "%");
            }

            return query.mapTo(Integer.class).one();
        });
    }


}
