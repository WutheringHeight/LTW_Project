// Source - https://stackoverflow.com/a
// Posted by Ali, modified by community. See post 'Timeline' for change history
// Retrieved 2025-12-08, License - CC BY-SA 4.0

package com.Aviary.controller;

import com.Aviary.components.Category;
import com.Aviary.components.Product;
import com.Aviary.service.CategoryService;
import com.Aviary.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductControllerServlet", value = "/product")
public class ProductController extends HttpServlet {
    private ProductService productService = new ProductService();
    private CategoryService categoryService = new CategoryService();
    public void init() {
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Category> categories = categoryService.getAllCategories();
        String categoryId = request.getParameter("categoryId");
        List<Product> products = productService.getAllProducts();

        request.setAttribute("categories", categories);
        request.setAttribute("products", products);
        request.getRequestDispatcher("HomePage/product.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Category> categories = categoryService.getAllCategories();
        String price = request.getParameter("price");
        String category = request.getParameter("categoryId");
        String sort = request.getParameter("sort");
        String kind = request.getParameter("kind");
        List<Product> products = productService.filterAndSort(null,price, category, kind, sort);

        request.setAttribute("categories", categories);
        request.setAttribute("price", price);
        request.setAttribute("category", category);
        request.setAttribute("kind", kind);
        request.setAttribute("sort", sort);
        request.setAttribute("products", products);
        request.setAttribute("param", request.getParameterMap()); // để JSP giữ lại giá trị
        request.getRequestDispatcher("HomePage/product.jsp").forward(request, response);
    }
    public void destroy() {
    }
}
