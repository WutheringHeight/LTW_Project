// Source - https://stackoverflow.com/a
// Posted by Ali, modified by community. See post 'Timeline' for change history
// Retrieved 2025-12-08, License - CC BY-SA 4.0

package com.Aviary.controller;

import com.Aviary.components.Product;
import com.Aviary.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ProductDetailControllerServlet", value = "/productdetail")
public class ProductDetailController extends HttpServlet {

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ProductService productService = new ProductService();
        String idStr = request.getParameter("id");
        int id = Integer.parseInt(idStr);

        Product product = productService.getProductById(id);
        request.setAttribute("product", product);
        request.getRequestDispatcher("productDetail/productDetail.jsp").forward(request,response);
    }

    public void destroy() {
    }
}
