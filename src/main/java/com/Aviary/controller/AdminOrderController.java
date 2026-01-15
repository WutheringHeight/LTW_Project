// Source - https://stackoverflow.com/a
// Posted by Ali, modified by community. See post 'Timeline' for change history
// Retrieved 2025-12-08, License - CC BY-SA 4.0

package com.Aviary.controller;

import java.io.*;
import java.util.List;

import com.Aviary.components.Order;
import com.Aviary.service.OrderService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/AdminOrder")
public class AdminOrderController extends HttpServlet {
    private OrderService orderService = new OrderService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = 1;
        int pageSize = 10;
        String pageParam = req.getParameter("page");
        if (pageParam != null) {
            page = Integer.parseInt(pageParam);
        }
        List<Order> orders = orderService.getOrdersPage(page, pageSize);
        int totalPages = orderService.getTotalPages(pageSize);

        req.setAttribute("orders", orders);
        req.setAttribute("currentPage", page);
        req.setAttribute("totalPages", totalPages);

        req.getRequestDispatcher("AdminPage/adminOrder.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");
        try {
            if ("updateStatus".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                String status = req.getParameter("status");
                orderService.updateStatus(id, status);
                req.getSession().setAttribute("success", "Cập nhật trạng thái thành công");
            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                orderService.deleteOrder(id);
                req.getSession().setAttribute("success", "Xóa đơn hàng thành công");
            }
        } catch (Exception e) {
            req.getSession().setAttribute("error", e.getMessage());
        }
        resp.sendRedirect(req.getContextPath() + "/AdminOrder");
    }
}
