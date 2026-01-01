// Source - https://stackoverflow.com/a
// Posted by Ali, modified by community. See post 'Timeline' for change history
// Retrieved 2025-12-08, License - CC BY-SA 4.0

package com.Aviary.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.Aviary.components.CartItem;
import com.Aviary.service.CartService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/cart")
public class CartController extends HttpServlet {
    public CartService cartService = new CartService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
            session.setAttribute("cart", cart);
        }

        String action = request.getParameter("action");

        // CHỈ xử lý các logic tính toán nếu có action
        if (action != null) {
            try {
                String idStr = request.getParameter("id");
                if (idStr == null || idStr.isEmpty()) {
                    response.sendRedirect("cart"); // Quay lại trang giỏ hàng nếu thiếu id
                    return;
                }
                int id = Integer.parseInt(idStr);

                if ("add".equals(action)) {
                    String qtyParam = request.getParameter("quantity");
                    int quantity = (qtyParam != null && !qtyParam.isEmpty()) ? Integer.parseInt(qtyParam) : 1;
                    cartService.addToCart(cart, id, quantity);

                    if ("checkout".equals(request.getParameter("redirect"))) {
                        response.sendRedirect("checkout");
                    } else {
                        response.sendRedirect("cart");
                    }
                    return;
                } else if ("update".equals(action)) {
                    String deltaParam = request.getParameter("delta");
                    int delta = (deltaParam != null) ? Integer.parseInt(deltaParam) : 0;
                    cartService.updateCart(cart, id, delta);
                } else if ("remove".equals(action)) {
                    cart.remove(id);
                }

                response.sendRedirect("cart");
                return;
            } catch (NumberFormatException e) {
                // Log lỗi hoặc chuyển hướng nếu ID không phải là số
                response.sendRedirect("cart");
                return;
            }
        }

        // Nếu action == null, code sẽ chạy xuống đây để hiển thị trang JSP
        request.setAttribute("cartItems", cart.values());
        request.setAttribute("totalCartPrice", cartService.calculateTotal(cart));
        request.getRequestDispatcher("/CartPage/cart.jsp").forward(request, response);
    }
}
