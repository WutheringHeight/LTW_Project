// Source - https://stackoverflow.com/a
// Posted by Ali, modified by community. See post 'Timeline' for change history
// Retrieved 2025-12-08, License - CC BY-SA 4.0

package com.Aviary.controller;

import java.io.*;
import java.util.Map;

import com.Aviary.components.CartItem;
import com.Aviary.service.OrderService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/checkout")
public class CheckoutController extends HttpServlet {

    private OrderService orderService = new OrderService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/CheckoutPage/checkout.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession();
        Map<Integer, CartItem> cart =
                (Map<Integer, CartItem>) session.getAttribute("cart");

        orderService.placeOrder(
                cart,
                request.getParameter("name"),
                request.getParameter("phone"),
                request.getParameter("address")
        );

        session.removeAttribute("cart");
        response.sendRedirect("home");
    }
}
