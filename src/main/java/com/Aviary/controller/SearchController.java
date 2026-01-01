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
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "SearchControllerServlet", value = "/search")
public class SearchController extends HttpServlet {
    private ProductService productService = new ProductService();
    private CategoryService categoryService = new CategoryService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Category> categories = categoryService.getAllCategories();
        String keyword = request.getParameter("q");
        String categoryId = request.getParameter("categoryId");
        List<Product> products;

        if (categoryId != null && !categoryId.isEmpty()) {
            products = productService.getProductsByCategory(Integer.parseInt(categoryId));
        } else {
            products = productService.searchProducts(keyword);
        }

        String price = request.getParameter("price");
        request.setAttribute("categories", categories);
        request.setAttribute("products", products);
        request.getRequestDispatcher("HomePage/SearchResult.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories = categoryService.getAllCategories();
        String keyword = request.getParameter("q");
        String category = request.getParameter("categoryId");
        String price;
        String kind;
        String sort;
        List<Product> products;
        List<Product> filtered;
        System.out.println("categoryId = " + request.getParameter("categoryId"));
        System.out.println("q = " + request.getParameter("q"));
        if (category != null && !category.isEmpty()) {
            products = productService.getProductsByCategory(Integer.parseInt(category));
            price = request.getParameter("price");
            kind = request.getParameter("kind");
            sort = request.getParameter("sort");
            filtered = productService.filterAndSortForGallery(products, price, category, kind, sort);
            System.out.println(products.size());
        } else {
            products = productService.searchProducts(keyword);
            price = request.getParameter("price");
            kind = request.getParameter("kind");
            sort = request.getParameter("sort");

            filtered = productService.filterAndSort(products, price, category, kind, sort);
        }

        System.out.println(filtered.size());
        request.setAttribute("categories", categories);
        request.setAttribute("products", filtered);
        request.setAttribute("price", price);
        request.setAttribute("category", category);
        request.setAttribute("kind", kind);
        request.setAttribute("sort", sort);
        request.setAttribute("q", keyword);
        request.setAttribute("param", request.getParameterMap());
        request.getRequestDispatcher("HomePage/SearchResult.jsp").forward(request, response);
    }

    public void destroy() {
    }
}
