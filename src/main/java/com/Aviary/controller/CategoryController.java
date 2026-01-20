package com.Aviary.controller;

import com.Aviary.components.Category;
import com.Aviary.service.CategoryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.List;
//@WebServlet("/CategoryController")
//@MultipartConfig
//public class CategoryController extends HttpServlet {
//    private CategoryService service = new CategoryService();
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
//            throws ServletException, IOException {
//        String action = req.getParameter("action");
//        try {
//            if ("add".equals(action)) {
//                String name = req.getParameter("categoryName");
//                Part filePart = req.getPart("pathImage");
//                if (name == null || name.trim().isEmpty()) {
//                    throw new RuntimeException("Tên category không được để trống");
//                }
//                if (service.existsByName(name)) {
//                    throw new RuntimeException("Tên chủ đề đã tồn tại");
//                }
//
//                    if (filePart == null || filePart.getSize() == 0) {
//                    throw new RuntimeException("Vui lòng chọn ảnh cho category");
//                }
//
//                String contentType = filePart.getContentType();
//                if (contentType == null || !contentType.startsWith("image/")) {
//                    throw new RuntimeException("File tải lên phải là ảnh");
//                }
//
//
//                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
//                String uploadPath = "E:/2025learn/uploads";
//                File uploadDir = new File(uploadPath);
//                if (!uploadDir.exists()) uploadDir.mkdir();
//                filePart.write(uploadPath + File.separator + fileName);
//
//                Category c = new Category();
//                c.setName(name);
//                c.setPathImage("uploads/" + fileName);
//                service.add(c);
//
//            } else if ("update".equals(action)) {
//                int id = Integer.parseInt(req.getParameter("id"));
//                String name = req.getParameter("categoryName");
//                if (service.existsByNameExceptId(name, id)) {
//                    throw new RuntimeException("Tên chủ đề đã tồn tại");
//                }
//                Part filePart = req.getPart("pathImage");
//                String pathImage = null;
//                if (filePart != null && filePart.getSize() > 0) {
//                    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
//                    String uploadPath = "E:/2025learn/uploads";
//                    File uploadDir = new File(uploadPath);
//                    if (!uploadDir.exists()) uploadDir.mkdir();
//                    filePart.write(uploadPath + File.separator + fileName);
//                    pathImage = "uploads/" + fileName;
//                }
//                Category c = new Category();
//                c.setId(id);
//                c.setName(name);
//                if (pathImage != null) c.setPathImage(pathImage);
//                service.update(c);
//                System.out.println(id + " " + name + " " + pathImage);
//            } else if ("delete".equals(action)) {
//                int id = Integer.parseInt(req.getParameter("id"));
//                service.delete(id);
//            }
//            req.getSession().setAttribute("msg", "Thành công");
//            resp.sendRedirect(req.getContextPath() + "/Admin");
//        } catch (Exception e) {
//            req.getSession().setAttribute("msg", "Lỗi: " + e.getMessage());
//            resp.sendRedirect(req.getContextPath() + "/Admin");
//        }
//    }
//}
@WebServlet("/CategoryController")
@MultipartConfig
public class CategoryController extends HttpServlet {
    private final CategoryService service = new CategoryService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        try {
            if ("add".equals(action)) {
                String name = req.getParameter("categoryName");
                Part filePart = req.getPart("pathImage");

                String pathImage = saveImage(filePart);

                Category c = new Category();
                c.setName(name);
                c.setPathImage(pathImage);

                service.add(c);

            } else if ("update".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                String name = req.getParameter("categoryName");

                Part filePart = req.getPart("pathImage");
                String pathImage = null;
                if (filePart != null && filePart.getSize() > 0) {
                    pathImage = saveImage(filePart);
                }

                Category c = new Category();
                c.setId(id);
                c.setName(name);
                if (pathImage != null) c.setPathImage(pathImage);

                service.update(c);

            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                service.delete(id);
            }

            req.getSession().setAttribute("msg", "✅ Thành công");
            resp.sendRedirect(req.getContextPath() + "/Admin");

        } catch (Exception e) {
            req.getSession().setAttribute("msg", "❌ " + e.getMessage());
            resp.sendRedirect(req.getContextPath() + "/Admin");
        }
    }

    // ================= UPLOAD ẢNH =================
    private String saveImage(Part filePart) throws IOException {
        if (filePart == null || filePart.getSize() == 0) {
            throw new RuntimeException("Vui lòng chọn ảnh cho category");
        }

        String contentType = filePart.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new RuntimeException("File tải lên phải là ảnh");
        }

        String fileName = Paths.get(filePart.getSubmittedFileName())
                .getFileName().toString();

        String uploadPath = "E:/2025learn/uploads";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();

        filePart.write(uploadPath + File.separator + fileName);

        return "uploads/" + fileName;
    }
}
