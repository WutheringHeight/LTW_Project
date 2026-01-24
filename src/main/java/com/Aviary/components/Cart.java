package com.Aviary.components;

import java.util.HashMap;

import com.Aviary.dao.ProductDAO;
import com.Aviary.dao.ReceiptDao;

public class Cart {
    private HashMap<Product, Integer> content;
    
    public void addToCart(Product p){
        content.put(p, content.getOrDefault(p, 0) + 1);
    }
    public void addToCart(Product p, int i){
        content.put(p, content.getOrDefault(p, 0) + i);
    }
    public void removeFromCart(Product p){
        content.put(p, content.getOrDefault(p, 0) - 1);
        if(content.get(p) <= 0) content.remove(p);
    }
    public void removeFromCart(Product p, int i){
        content.put(p, content.getOrDefault(p, 0) - i);
        if(content.get(p) <= 0) content.remove(p);
    }
    public void emptyCart(){
        content.clear();
    }
    
    public int checkout(int accountID) throws IllegalStateException{
        for (Product product : content.keySet()){
            int upToDateStock = ProductDAO.getProductById(product.getId()).getStock();
            if(upToDateStock < content.get(product))
                throw new IllegalStateException("Cart ask for more product than exist within stock");
        }
        int receiptID = ReceiptDao.createReceipt(accountID);
        for (Product product : content.keySet()) {
            ReceiptDao.createReceiptItem(receiptID, product.getId(), content.get(product), product.getPrice());
            ProductDAO.updateAfterCheckout(product.getId(),content.get(product));
        }
        return receiptID;
    }
}
