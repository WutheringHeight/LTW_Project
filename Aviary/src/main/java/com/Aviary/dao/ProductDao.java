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
    
}
