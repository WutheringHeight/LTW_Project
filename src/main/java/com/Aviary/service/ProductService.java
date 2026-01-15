package com.Aviary.service;

import com.Aviary.components.Category;
import com.Aviary.dao.ProductDAO;
import com.Aviary.components.Product;

import java.util.List;

public class ProductService {
    private ProductDAO productDAO = new ProductDAO();

    public List<Product> getLatestProducts() {
        return productDAO.getLatestProducts();
    }

    public Product getProductById(int id) {
        return productDAO.getProductById(id);
    }
    public List<Product> getProductsByCategory(int category) {return productDAO.getProductsByCategory(category);}

    public List<Product> getPopularProducts() {
        return productDAO.getPopularProducts();
    }
    public List<Product> searchProducts(String keyword) {return productDAO.searchProducts(keyword);}
    public List<Product> filterAndSort(List<Product> products, String price, String category,String kind, String sort) {
        return productDAO.filterAndSort(products,price, category, kind, sort);
    }
    public List<Product> filterAndSortForGallery(List<Product> products, String price, String category,String kind, String sort){
        return productDAO.filterAndSortForGallery(products,price, category, kind, sort);
    }
    public int addProduct(Product p) {
        return productDAO.addProduct(p);
    }

    public void deleteProduct(int id) {
        productDAO.deleteProduct(id);
    }

    public void updateProduct(Product p) {
        productDAO.updateProduct(p);
    }

    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }
    public List<Category> getAllCategories() {return productDAO.getAllCategories();}
}
