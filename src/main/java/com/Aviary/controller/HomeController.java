// Source - https://stackoverflow.com/a
// Posted by Ali, modified by community. See post 'Timeline' for change history
// Retrieved 2025-12-08, License - CC BY-SA 4.0

package com.Aviary.controller;

import com.Aviary.components.Product;
import com.Aviary.components.Category;
import com.Aviary.service.CategoryService;
import com.Aviary.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomeControllerServlet", value = "/home")
public class HomeController extends HttpServlet {
    private ProductService productService = new ProductService();
    private CategoryService categoryService = new CategoryService();

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        List<Product> latestProducts = productService.getLatestProducts();
        List<Product> popularProducts = productService.getPopularProducts();
        List<Category> categories = categoryService.getAllCategories();
        request.setAttribute("categories", categories);
        request.setAttribute("popularProducts", popularProducts);
        request.setAttribute("latestProducts", latestProducts);

        request.getRequestDispatcher("HomePage/home.jsp").forward(request,response);
    }

    public void destroy() {
    }
}
