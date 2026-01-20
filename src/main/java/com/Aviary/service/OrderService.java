package com.Aviary.service;

import com.Aviary.components.CartItem;
import com.Aviary.dao.OrderDAO;
import com.Aviary.components.Order;
import com.Aviary.components.OrderItem;
import com.Aviary.dao.ProductDAO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService {

    private OrderDAO orderDAO = new OrderDAO();
    private ProductDAO productDAO = new ProductDAO();
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

            productDAO.updateAfterCheckout(c.getId(), c.getQuantity());
        }

        orderDAO.insertOrderItems(orderId, items);
    }

    public Map<String, String> validate(String name, String phone, String address) {
        Map<String, String> errors = new HashMap<>();

        if (name == null || name.trim().length() < 3) {
            errors.put("name", "Họ tên phải có ít nhất 3 ký tự");
        }

        if (phone == null || !phone.matches("^0\\d{9}$")) {
            errors.put("phone", "Số điện thoại không hợp lệ (10 số, bắt đầu bằng 0)");
        }

        if (address == null || address.trim().length() < 10) {
            errors.put("address", "Địa chỉ phải rõ ràng (ít nhất 10 ký tự)");
        }

        return errors;
    }

    public List<Order> getAllOrders() {
        List<Order> orders = orderDAO.findAll();
        for (Order o : orders) {
            o.setItems(orderDAO.findItemsByOrderId(o.getId()));
        }
        return orders;
    }

    public void deleteOrder(int id) {
        orderDAO.delete(id);
    }

    public List<Order> getOrdersPage(int page, int pageSize,String status, String fromDate, String toDate,String keyword) {
        List<Order> orders = orderDAO.findPage(page, pageSize,status, fromDate, toDate,keyword);
        for (Order o : orders) {
            o.setItems(orderDAO.findItemsByOrderId(o.getId()));
        }
        return orders;
    }

    public int getTotalPages( int pageSize, String status, String fromDate, String toDate,String keyword ){
        int total = orderDAO.countOrders(status, fromDate, toDate,keyword);
        return (int) Math.ceil((double) total/ pageSize);
    }

    public void updateStatus(int id, String status) {
        if ("Cancelled".equalsIgnoreCase(status)) {

            List<OrderItem> items = orderDAO.findItemsByOrderId(id);

            for (OrderItem item : items) {
                productDAO.returnStockAfterCancel(item.getProductId(), item.getQuantity());
            }
        }
        // 3. Cập nhật trạng thái đơn hàng
        orderDAO.updateStatus(id, status);
    }
}

