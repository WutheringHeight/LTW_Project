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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");

        if (cart == null) {
            cart = new HashMap<>();
            session.setAttribute("cart", cart);
        }

        String action = request.getParameter("action");

        if ("add".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            cartService.addToCart(cart, id, quantity);
            response.sendRedirect("cart");
            return;
        }

        if ("update".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            int delta = Integer.parseInt(request.getParameter("delta"));

            cartService.updateCart(cart, id, delta);
            response.sendRedirect("cart");
            return;
        }

        if ("remove".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            cart.remove(id);
            response.sendRedirect("cart");
            return;
        }

        // ======= CART =======
        request.setAttribute("cartItems", cart.values());
        request.setAttribute("totalCartPrice", cartService.calculateTotal(cart));
        request.getRequestDispatcher("/CartPage/cart.jsp")
                .forward(request, response);
    }
}
