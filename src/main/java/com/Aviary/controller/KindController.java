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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");
        try {
            switch (action) {
                case "add":
                    service.add(req.getParameter("kindName"));
                    break;

                case "update":
                    service.update(
                            req.getParameter("oldName"),
                            req.getParameter("newName")
                    );
                    break;

                case "delete":
                    service.delete(req.getParameter("name"));
                    break;
            }

            resp.getWriter().print(
                    "{\"success\":true,\"message\":\"Thao tác thành công\"}"
            );

        } catch (Exception e) {
            String msg = e.getMessage().replace("\"", "'");
            resp.getWriter().print(
                    "{\"success\":false,\"message\":\"" + msg + "\"}"
            );
        }
    }
}
