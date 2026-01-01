// Source - https://stackoverflow.com/a
// Posted by Ali, modified by community. See post 'Timeline' for change history
// Retrieved 2025-12-08, License - CC BY-SA 4.0

package com.Aviary.controller;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

import com.Aviary.ViewModel.ProductSuggest;
import com.Aviary.components.Product;
import com.Aviary.service.ProductService;
import com.google.gson.Gson;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "SuggestionsControllerServlet", value = "/api/suggestion")
public class SuggestionController extends HttpServlet {
    private ProductService productService = new ProductService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String keyword = request.getParameter("q");
        response.setContentType("application/json;charset=UTF-8");

        if (keyword == null || keyword.trim().isEmpty()) {
            response.getWriter().print("[]");
            return;
        }

        try {
            List<Product> products = productService.searchProducts(keyword);
            System.out.println("Keyword: " + keyword);
            System.out.println("Found products: " + products.size());

            List<ProductSuggest> suggestions = products.stream()
                    .limit(4)
                    .map(ProductSuggest::new)
                    .collect(Collectors.toList());

            response.getWriter().print(new Gson().toJson(suggestions));


        } catch (Exception e) {
            e.printStackTrace(); // bắt buộc
            response.setStatus(500);
            response.getWriter().print("[]");
        }
    }

    public void destroy() {
    }
}
