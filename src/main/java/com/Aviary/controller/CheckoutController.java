// Source - https://stackoverflow.com/a
// Posted by Ali, modified by community. See post 'Timeline' for change history
// Retrieved 2025-12-08, License - CC BY-SA 4.0

package com.Aviary.controller;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "CheckoutControllerServlet", value = "/CheckoutController-servlet")
public class CheckoutController extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}
