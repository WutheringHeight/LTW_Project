// Source - https://stackoverflow.com/a
// Posted by Ali, modified by community. See post 'Timeline' for change history
// Retrieved 2025-12-08, License - CC BY-SA 4.0

package com.Aviary.controller;

import java.io.*;

import com.Aviary.service.KindService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/KindController")
public class KindController extends HttpServlet {
    private KindService service = new KindService();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "add":
                    service.add(request.getParameter("kindName"));
                    request.getSession().setAttribute("msg", "Thêm loại thành công");
                    break;

                case "update":
                    service.update(
                            request.getParameter("oldName"),
                            request.getParameter("newName")
                    );
                    request.getSession().setAttribute("msg", "Cập nhật loại thành công");
                    break;

                case "delete":
                    service.delete(request.getParameter("name"));
                    request.getSession().setAttribute("msg", "Xóa loại thành công");
                    break;
            }
            request.setAttribute("kinds", service.getAll());
            response.sendRedirect(request.getContextPath() + "/Admin");

        } catch (Exception e) {
            request.setAttribute("kinds", service.getAll());
            request.getSession().setAttribute("msg", "Lỗi: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/Admin");

        }
    }

}
