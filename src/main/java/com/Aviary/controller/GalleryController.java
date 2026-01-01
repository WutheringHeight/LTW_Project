// Source - https://stackoverflow.com/a
// Posted by Ali, modified by community. See post 'Timeline' for change history
// Retrieved 2025-12-08, License - CC BY-SA 4.0

package com.Aviary.controller;

import java.io.*;
import java.util.List;

import com.Aviary.components.Category;
import com.Aviary.components.Product;
import com.Aviary.service.CategoryService;
import com.Aviary.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "GalleryControllerServlet", value = "/gallery")
public class GalleryController extends HttpServlet {
    private CategoryService categoryService = new CategoryService();
    private ProductService productService = new ProductService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Product> latestProducts = productService.getLatestProducts();
        List<Product> popularProducts = productService.getPopularProducts();
        List<Category> categories = categoryService.getAllCategories();
        request.setAttribute("categories", categories);
        request.setAttribute("popularProducts", popularProducts);
        request.setAttribute("latestProducts", latestProducts);

        List<Category> items = categoryService.getAllCategories();
        request.setAttribute("categories", items);
        request.getRequestDispatcher("HomePage/home.jsp").forward(request, response);
    }

    public void destroy() {
    }
}
