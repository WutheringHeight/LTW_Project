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
            throws IOException, ServletException {

        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        Map<String, String> errors = orderService.validate(name, phone, address);

        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.setAttribute("oldName", name);
            request.setAttribute("oldPhone", phone);
            request.setAttribute("oldAddress", address);
            request.setAttribute("autoCheckout", true);

            HttpSession session = request.getSession();
            Map<Integer, CartItem> cart =
                    (Map<Integer, CartItem>) session.getAttribute("cart");
            if (cart != null) {
                request.setAttribute("cartItems", cart.values());
                request.setAttribute("totalCartPrice",
                        cart.values().stream().mapToDouble(CartItem::getTotal).sum());
            }

            request.getRequestDispatcher("/CartPage/cart.jsp")
                    .forward(request, response);
            return;
        }

        HttpSession session = request.getSession();
        Map<Integer, CartItem> cart =
                (Map<Integer, CartItem>) session.getAttribute("cart");

        orderService.placeOrder(cart, name, phone, address);

        session.removeAttribute("cart");
        response.sendRedirect(request.getContextPath() + "/CheckoutPage/OrderSuccess.jsp");
    }
}
