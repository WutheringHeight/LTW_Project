package com.Aviary.dao;

import java.util.List;

import com.Aviary.components.JDBIProvider;
import com.Aviary.components.Product;

public class ProductDao {
    public static List<Product> getProductInCategory(String category){
        return JDBIProvider.get().withHandle(handle ->{
            return handle.createQuery("select * from Product where category = :category;")
            .bind("category", category)
            .mapToBean(Product.class)
            .list();
        });
    }
    public static Product getProduct(int id){
        return JDBIProvider.get().withHandle(handle -> {
            try{
                return handle.createQuery("select * from Product where ID = :PID;")
                .bind("PID", id)
                .mapToBean(Product.class)
                .one();
            }catch(Exception e){
                //Product doesn't exist
                return null;
            }
        });
    }
    public static List<Product> getProductByName(String name){
        return JDBIProvider.get().withHandle(handle -> {
            return handle.createQuery("select * from Product where productName like '%' + :keyword + '%';")
            .bind("keyword", name)
            .mapToBean(Product.class)
            .list();
        });
    }
    public static List<Product> getNewestProduct(int amount){
        return JDBIProvider.get().withHandle(handle -> {
            return handle.createQuery("select top :amount * from Product order by createdAt desc;")
            .bind("amount", amount)
            .mapToBean(Product.class)
            .list();
        });
    }
    public static List<Product> getPopularProduct(int amount){
        return JDBIProvider.get().withHandle(handle -> {
            return handle.createQuery("select top :amount * from Product order by soldQuantity desc;")
            .bind("amount", amount)
            .mapToBean(Product.class)
            .list();
        });
    }
    public static List<Product> getRelatedProduct(int amount, int productID){
        return JDBIProvider.get().withHandle(handle -> {
            return handle.createQuery("select top :amount * from Product where category = (select category from Product where PID = :PID)and PID <> :PID;")
                .bind("amount",amount)
                .bind("PID", productID)
                .mapToBean(Product.class)
                .list();
        });
    }
    public static int checkStock(int productID){
        return JDBIProvider.get().withHandle(handle -> {
            try{
                return handle.createQuery("select stockQuantity from Product where ID = :PID;")
                .bind("PID", productID)
                .mapToBean(Integer.class)
                .one();
            }catch(Exception e){
                //product doesn't exist
                return -1;
            }
        });
    }
    public static void updateStock(int receiptID){
        JDBIProvider.get().useHandle(handle -> 
            handle.createUpdate("update Product set stock = stock - r.quantity, soldQuantity = soldQuantity + r.quantity from Product p join Receipt r on p.PID = r.PID where r.ID = :receipt_ID;")
            .bind("receipt_ID", receiptID)
            .execute()
        );
    }
}
