package com.Aviary.service;

import com.Aviary.components.CartItem;
import com.Aviary.components.Product;
import com.Aviary.dao.ProductDAO;

import java.util.Map;

public class CartService {
    private ProductDAO productDAO = new ProductDAO();

    public void updateCart(Map<Integer, CartItem> cart, int productId, int delta){
        if(cart.containsKey(productId)){
            CartItem item = cart.get(productId);
            Product product = productDAO.getProductById(productId);
            int stock = product.getStock();

            int newQty = item.getQuantity() + delta;
            if (newQty <= 0) {
                cart.remove(productId);
            } if (newQty > stock) {
                newQty = stock;
            }
            item.setQuantity(newQty);
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
        Product product = productDAO.getProductById(productId);

        if (product.getStock() <= 0) {
            return;
        }
        int newQty = quantity;

        if (cart.containsKey(productId)) {
            CartItem item = cart.get(productId);
            newQty = item.getQuantity() + quantity;
            if (newQty > product.getStock()) {
                newQty = product.getStock();
            }
            item.setQuantity(item.getQuantity() + newQty);

        } else {
            CartItem newItem = productDAO.getProductForCart(productId);
            if (newItem != null) {
                if (newQty > product.getStock()) {
                    newQty = product.getStock();
                }
                newItem.setQuantity(newQty);
                cart.put(productId, newItem);
            }
        }
    }

}
