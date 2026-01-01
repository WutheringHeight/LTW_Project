package com.Aviary.service;

import com.Aviary.components.CartItem;
import com.Aviary.dao.ProductDAO;

import java.util.Map;

public class CartService {
    private ProductDAO productDAO = new ProductDAO();

    public void updateCart(Map<Integer, CartItem> cart, int productId, int delta){
        if(cart.containsKey(productId)){
            CartItem item = cart.get(productId);
            int newQty = item.getQuantity() + delta;
            if (newQty <= 0) {
                cart.remove(productId);
            } else {
                item.setQuantity(newQty);
            }
        } else if (delta > 0) {
            CartItem newItem = productDAO.getProductForCart(productId);
            if (newItem != null) {
                newItem.setQuantity(1);
                cart.put(productId, newItem);
            }
        }
    }

    // Tính tổng tiền toàn bộ giỏ hàng
    public double calculateTotal(Map<Integer, CartItem> cart) {
        return cart.values().stream().mapToDouble(CartItem::getTotal).sum();
    }
    public void addToCart(Map<Integer, CartItem> cart, int productId, int quantity) {
        if (cart.containsKey(productId)) {
            CartItem item = cart.get(productId);
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            CartItem newItem = productDAO.getProductForCart(productId);
            if (newItem != null) {
                newItem.setQuantity(quantity);
                cart.put(productId, newItem);
            }
        }
    }

}
