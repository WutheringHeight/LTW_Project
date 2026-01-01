package com.Aviary.controller;

import com.Aviary.components.Product;
import com.Aviary.components.ProductImage;
import com.Aviary.service.ProductImageService;
import com.Aviary.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@WebServlet(name = "AdminController", value = "/Admin")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50
)
public class AdminController extends HttpServlet {
    private ProductService productService = new ProductService();
    private ProductImageService productImageService = new ProductImageService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if ("add".equals(action)) {
            Product p = new Product();
            p.setProductName(request.getParameter("productName"));
            p.setPrice(Double.parseDouble(request.getParameter("price")));
            p.setCategory_id(Integer.parseInt(request.getParameter("category")));
            p.setKind(request.getParameter("kind"));
            p.setStock(Integer.parseInt(request.getParameter("stock")));
            p.setDescription(request.getParameter("description"));
            System.out.println("1");
            // Upload ảnh
            Part filePart = request.getPart("thumbnail");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String uploadPath = "E:/2025learn/uploads";
            System.out.println("Upload path: " + uploadPath);
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();
            filePart.write(uploadPath + File.separator + fileName);
            p.setThumbnail("uploads/" + fileName);

            int productId = productService.addProduct(p);
            System.out.println("2");
            // Upload extra images
            String[] extraNames = {"extraImage1", "extraImage2"};
            for (String name : extraNames) {
                Part part = request.getPart(name);
                if (part != null && part.getSize() > 0) {
                    String fileNamei = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    part.write(uploadPath + File.separator + fileNamei);
                    ProductImage pi = new ProductImage();
                    pi.setProductId(productId);
                    pi.setImageUrl("uploads/" + fileNamei);
                    productImageService.addProductImage(pi);
                }
            }
            response.sendRedirect(request.getContextPath() + "/Admin");
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            productService.deleteProduct(id);

            response.sendRedirect(request.getContextPath() + "/Admin");
        } else if ("edit".equals(action)) {
            Product p = new Product();
            p.setId(Integer.parseInt(request.getParameter("id")));
            p.setProductName(request.getParameter("productName"));
            p.setPrice(Double.parseDouble(request.getParameter("price")));
            p.setCategory_id(Integer.parseInt(request.getParameter("category")));
            p.setKind(request.getParameter("kind"));
            p.setStock(Integer.parseInt(request.getParameter("stock")));
            p.setDescription(request.getParameter("description"));
            // xử lý upload ảnh nếu có
            productService.updateProduct(p);
            System.out.println("3");
            response.sendRedirect(request.getContextPath() + "/Admin");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // load danh sách sản phẩm
        List<Product> products = productService.getAllProducts();
        request.setAttribute("products", products);
        request.getRequestDispatcher("AdminPage/adminProduct.jsp").forward(request, response);
    }
}
