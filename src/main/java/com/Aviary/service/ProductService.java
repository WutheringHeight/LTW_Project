package com.Aviary.service;

import com.Aviary.components.Category;
import com.Aviary.components.ProductImage;
import com.Aviary.dao.ProductDAO;
import com.Aviary.components.Product;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private ProductDAO productDAO = new ProductDAO();
    private ProductImageService productImageService = new ProductImageService();

    public List<Product> getLatestProducts() {
        return productDAO.getLatestProducts();
    }

    public Product getProductById(int id) {
        return productDAO.getProductById(id);
    }
    public List<Product> getProductsByCategory(int category) {return productDAO.getProductsByCategory(category);}

    public List<Product> getPopularProducts() {
        return productDAO.getPopularProducts();
    }
    public List<Product> searchProducts(String keyword) {return productDAO.searchProducts(keyword);}
    public List<Product> filterAndSort(List<Product> products, String price, String category,String kind, String sort) {
        return productDAO.filterAndSort(products,price, category, kind, sort);
    }
    public List<Product> filterAndSortForGallery(List<Product> products, String price, String category,String kind, String sort){
        return productDAO.filterAndSortForGallery(products,price, category, kind, sort);
    }
    public int addProduct(Product p) {
        return productDAO.addProduct(p);
    }
    public void addProductWithImages(HttpServletRequest request) {
        try {
            // ===== VALIDATE TEXT =====
            String name = request.getParameter("productName");
            if (name == null || name.trim().isEmpty())
                throw new RuntimeException("Tên sản phẩm không được để trống");

            if (productDAO.existsByName(name))
                throw new RuntimeException("Tên sản phẩm đã tồn tại");

            double price = Double.parseDouble(request.getParameter("price"));
            if (price <= 0) throw new RuntimeException("Giá không hợp lệ");

            int stock = Integer.parseInt(request.getParameter("stock"));
            if (stock < 0) throw new RuntimeException("Số lượng không hợp lệ");

            int categoryId = Integer.parseInt(request.getParameter("category"));
            String kind = request.getParameter("kind");
            if (kind == null || kind.trim().isEmpty())
                throw new RuntimeException("Loại sản phẩm không được để trống");

            // ===== UPLOAD PATH =====
            String uploadPath = "E:/2025learn/uploads";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();

            // ===== THUMBNAIL (BẮT BUỘC) =====
            Part thumbnailPart = request.getPart("thumbnail");
            if (thumbnailPart == null || thumbnailPart.getSize() == 0)
                throw new RuntimeException("Vui lòng chọn ảnh đại diện");

            if (!thumbnailPart.getContentType().startsWith("image/"))
                throw new RuntimeException("Ảnh đại diện phải là ảnh");

            String thumbName = Paths.get(thumbnailPart.getSubmittedFileName())
                    .getFileName().toString();
            thumbnailPart.write(uploadPath + File.separator + thumbName);

            // ===== SAVE PRODUCT =====
            Product p = new Product();
            p.setProductName(name);
            p.setPrice(price);
            p.setStock(stock);
            p.setCategory_id(categoryId);
            p.setKind(kind);
            p.setDescription(request.getParameter("description"));
            p.setThumbnail("uploads/" + thumbName);

            int productId = productDAO.addProduct(p);

            // ===== EXTRA IMAGES =====
            String[] extraFields = {"extraImage1", "extraImage2"};

            for (String field : extraFields) {
                Part part = request.getPart(field);
                if (part != null && part.getSize() > 0) {

                    if (!part.getContentType().startsWith("image/"))
                        throw new RuntimeException("Ảnh phụ phải là ảnh");

                    String fileName = Paths.get(part.getSubmittedFileName())
                            .getFileName().toString();
                    part.write(uploadPath + File.separator + fileName);

                    ProductImage img = new ProductImage();
                    img.setProductId(productId);
                    img.setImageUrl("uploads/" + fileName);

                    productImageService.addProductImage(img);
                }
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException("Dữ liệu số không hợp lệ");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    public void deleteProduct(int id) {
        productDAO.deleteProduct(id);
    }

    public void updateProduct(Product p, List<ProductImage> extraImages) {

        if (p.getProductName() == null || p.getProductName().trim().isEmpty()) {
            throw new RuntimeException("Tên sản phẩm không được để trống");
        }

        if (productDAO.existsByNameExceptId(p.getProductName(), p.getId())) {
            throw new RuntimeException("Tên sản phẩm đã tồn tại");
        }

        if (p.getPrice() <= 0) {
            throw new RuntimeException("Giá sản phẩm phải lớn hơn 0");
        }

        if (p.getStock() < 0) {
            throw new RuntimeException("Tồn kho không được âm");
        }

        // ===== UPDATE PRODUCT =====
        productDAO.updateProduct(p);

        // ===== EXTRA IMAGES  =====
        if (extraImages != null) {
            for (ProductImage img : extraImages) {
                productImageService.deleteByProductIdAndType(
                        p.getId(), img.getType()
                );

                img.setProductId(p.getId());
                productImageService.addProductImage(img);
            }
        }
    }


    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }
    public List<Category> getAllCategories() {return productDAO.getAllCategories();}

    public List<Product> getProductsPageWithFilter(String keyword, String categoryId, String kind, int page, int pageSize ) {
        List<Product> products = productDAO.findPageWithFilter(keyword, categoryId, kind, page, pageSize);
        if (products.isEmpty()) {
            return products;
        }

        List<Integer> productIds = new ArrayList<>();
        for (Product p : products) {
            productIds.add(p.getId());
            p.setImages(new ArrayList<>());
        }

        List<ProductImage> images = productDAO.findByProductIds(productIds);

        for (ProductImage img : images) {
            for (Product p : products) {
                if (p.getId() == img.getProductId()) {
                    p.getImages().add(img);
                    break;
                }
            }
        }

        return products;
    }
    public int countProductsWithFilter( String keyword, String categoryId, String kind,int pageSize ) {
        int totalProducts = productDAO.countWithFilter( keyword, categoryId, kind);
        return (int) Math.ceil((double) totalProducts / pageSize);
    }

}
