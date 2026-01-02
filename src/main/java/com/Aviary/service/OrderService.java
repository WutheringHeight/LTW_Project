package com.Aviary.service;

import com.Aviary.components.CartItem;
import com.Aviary.dao.OrderDAO;
import com.Aviary.components.Order;
import com.Aviary.components.OrderItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderService {

    private OrderDAO orderDAO = new OrderDAO();

    public void placeOrder(Map<Integer, CartItem> cart, String name, String phone, String address) {
        Order order = new Order();
        order.setCustomerName(name);
        order.setPhone(phone);
        order.setAddress(address);
        order.setStatus("PENDING");

        double total = cart.values().stream()
                .mapToDouble(CartItem::getTotal)
                .sum();
        order.setTotalPrice(total);


        int orderId = orderDAO.insertOrder(order);

        List<OrderItem> items = new ArrayList<>();
        for (CartItem c : cart.values()) {
            OrderItem item = new OrderItem();
            item.setOrderId(orderId);
            item.setProductId(c.getId());
            item.setProductName(c.getName());
            item.setPrice(c.getPrice());
            item.setQuantity(c.getQuantity());
            items.add(item);
        }

        orderDAO.insertOrderItems(orderId, items);
    }
}

