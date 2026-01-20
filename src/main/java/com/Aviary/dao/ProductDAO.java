package com.Aviary.dao;

import com.Aviary.components.*;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.Query;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProductDAO {

    private final Jdbi jdbi = JDBIProvider.get();

    public Product getProductById(int id) {
        return jdbi.withHandle(handle -> {
            // Lấy product
            Product product = handle.createQuery("SELECT * FROM product WHERE id = :id")
                    .bind("id", id)
                    .mapToBean(Product.class)
                    .findOne()
                    .orElse(null);

            if (product != null) {
                // Lấy danh sách ảnh
                List<ProductImage> images = handle.createQuery("SELECT * FROM product_image WHERE product_id = :id")
                        .bind("id", id)
                        .mapToBean(ProductImage.class)
                        .list();

                product.setImages(images);
            }

            return product;
        });
    }

    public List<Product> getProductsByCategory(int category) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT id, productName, price, kind, thumbnail FROM product where category_id = :id")
                        .bind("id", category)
                        .mapToBean(Product.class)
                        .list());
    }

    public List<Product> getLatestProducts() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT id, productName, price, thumbnail FROM product ORDER BY createdAt DESC LIMIT 8")
                        .mapToBean(Product.class)
                        .list());
    }

    public List<Product> getPopularProducts() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT id, productName, price, thumbnail FROM product ORDER BY soldQuantity DESC LIMIT 8")
                        .mapToBean(Product.class)
                        .list()
        );
    }

    public List<Product> searchProducts(String keyword) {
        String sql = "SELECT id, productName, price, thumbnail, category_id, kind " +
                "FROM product WHERE productName LIKE :keyword";

        return jdbi.withHandle(handle ->
                handle.createQuery(sql)
                        .bind("keyword", "%" + keyword + "%")
                        .mapToBean(Product.class)
                        .list()
        );
    }


    public List<Product> filterAndSort(List<Product> products, String price, String category, String kind, String sort) {
        // Nếu đã có danh sách product
        if (products != null && !products.isEmpty()) {
            Stream<Product> stream = products.stream();
            if (price != null && !price.isEmpty()) {
                switch (price) {
                    case "0-500000":
                        stream = stream.filter(p -> p.getPrice() < 500000);
                        break;
                    case "500000-1000000":
                        stream = stream.filter(p -> p.getPrice() >= 500000 && p.getPrice() <= 1000000);
                        break;
                    case "1000000-2000000":
                        stream = stream.filter(p -> p.getPrice() >= 1000000 && p.getPrice() <= 2000000);
                        break;
                    case "2000000+":
                        stream = stream.filter(p -> p.getPrice() > 2000000);
                        break;
                }
            }
            if (category != null && !category.isEmpty()) {
                int categoryId = Integer.parseInt(category); // ép String -> int
                stream = stream.filter(p -> p.getCategory_id() == categoryId);
            }
            if (kind != null && !kind.isEmpty()) {
                stream = stream.filter(p -> p.getKind().equalsIgnoreCase(kind));
            }
            if (sort != null && !sort.isEmpty()) {
                switch (sort) {
                    case "popular":
                        stream = stream.sorted(
                                Comparator.comparing(
                                        Product::getSoldQuantity,
                                        Comparator.nullsLast(Integer::compareTo)
                                ).reversed()
                        );
                        break;
                    case "newest":
                        stream = stream.sorted(
                                Comparator.comparing(
                                        Product::getCreatedAt,
                                        Comparator.nullsLast(java.time.LocalDateTime::compareTo)
                                ).reversed()
                        );
                        break;
                    case "priceAsc":
                        stream = stream.sorted(
                                Comparator.comparing(
                                        Product::getPrice,
                                        Comparator.nullsLast(Comparator.naturalOrder())));
                        break;
                    case "priceDesc":
                        stream = stream.sorted(
                                Comparator.comparing(
                                        Product::getPrice,
                                        Comparator.nullsLast(Comparator.naturalOrder())).reversed() );
                        break;
                }
            }

            return stream.collect(Collectors.toList());
        }
        StringBuilder sql = new StringBuilder("SELECT id, productName, price, thumbnail, category_id, kind FROM product WHERE 1=1");
        // lọc giá
        if (price != null && !price.isEmpty()) {
            switch (price) {
                case "0-500000":
                    sql.append(" AND price < 500000");
                    break;
                case "500000-1000000":
                    sql.append(" AND price BETWEEN 500000 AND 1000000");
                    break;
                case "1000000-2000000":
                    sql.append(" AND price BETWEEN 1000000 AND 2000000");
                    break;
                case "2000000+":
                    sql.append(" AND price > 2000000");
                    break;
            }
        }

        // lọc thể loại
        if (category != null && !category.isEmpty()) {
            sql.append(" AND category_id = :category_id");
        }
        if (kind != null && !kind.isEmpty()) {
            sql.append(" AND kind = :kind");
        }

        // sắp xếp
        if (sort != null) {
            switch (sort) {
                case "popular":
                    sql.append(" ORDER BY soldQuantity DESC");
                    break;
                case "newest":
                    sql.append(" ORDER BY createdAt DESC");
                    break;
                case "priceAsc":
                    sql.append(" ORDER BY price ASC");
                    break;
                case "priceDesc":
                    sql.append(" ORDER BY price DESC");
                    break;
            }
        }

        return jdbi.withHandle(handle -> {
            Query query = handle.createQuery(sql.toString());

            if (category != null && !category.isEmpty()) {
                query.bind("category_id", category);
            }
            if (kind != null && !kind.isEmpty()) {
                query.bind("kind", kind);
            }

            return query.mapToBean(Product.class).list();
        });
    }

    public List<Product> filterAndSortForGallery(List<Product> products, String price, String category, String kind, String sort) {
            Stream<Product> stream = products.stream();
            if (price != null && !price.isEmpty()) {
                switch (price) {
                    case "0-500000":
                        stream = stream.filter(p -> p.getPrice() < 500000);
                        break;
                    case "500000-1000000":
                        stream = stream.filter(p -> p.getPrice() >= 500000 && p.getPrice() <= 1000000);
                        break;
                    case "1000000-2000000":
                        stream = stream.filter(p -> p.getPrice() >= 1000000 && p.getPrice() <= 2000000);
                        break;
                    case "2000000+":
                        stream = stream.filter(p -> p.getPrice() > 2000000);
                        break;
                }
            }
//            if (category != null && !category.isEmpty()) {
//                int categoryId = Integer.parseInt(category); // ép String -> int
//                stream = stream.filter(p -> p.getCategory_id() == categoryId);
//            }
            if (kind != null && !kind.isEmpty()) {
                stream = stream.filter(p -> p.getKind().equalsIgnoreCase(kind));
            }
            if (sort != null && !sort.isEmpty()) {
                switch (sort) {
                    case "popular":
                        stream = stream.sorted(
                                Comparator.comparing(
                                        Product::getSoldQuantity,
                                        Comparator.nullsLast(Integer::compareTo)
                                ).reversed()
                        );
                        break;
                    case "newest":
                        stream = stream.sorted(
                                Comparator.comparing(
                                        Product::getCreatedAt,
                                        Comparator.nullsLast(java.time.LocalDateTime::compareTo)
                                ).reversed()
                        );
                        break;
                    case "priceAsc":
                        stream = stream.sorted(
                                Comparator.comparing(
                                        Product::getPrice,
                                        Comparator.nullsLast(Comparator.naturalOrder())));
                        break;
                    case "priceDesc":
                        stream = stream.sorted(
                                Comparator.comparing(
                                        Product::getPrice,
                                        Comparator.nullsLast(Comparator.naturalOrder())).reversed() );
                        break;
                }
            }
            return stream.collect(Collectors.toList());
    }


    public List<Product> getAllProducts() {
        return jdbi.withHandle(handle -> {
            List<Product> products = handle.createQuery(
                            "SELECT id, productName, price, category_id, kind, stock, thumbnail " +
                                    "FROM product ORDER BY createdAt DESC")
                    .mapToBean(Product.class)
                    .list();

            for (Product p : products) {
                List<ProductImage> images = handle.createQuery(
                                "SELECT id, product_id, image_url FROM product_image WHERE product_id = :pid")
                        .bind("pid", p.getId())
                        .mapToBean(ProductImage.class)
                        .list();
                p.setImages(images);
            }
            return products;
        });
    }


    public int addProduct(Product p) {
        return jdbi.withHandle(handle ->
                handle.createUpdate("INSERT INTO product (productName, price, category_id, kind, stock, thumbnail, description) " +
                                "VALUES (:productName, :price, :category, :kind, :stock, :thumbnail, :description)")
                        .bind("productName", p.getProductName())
                        .bind("price", p.getPrice())
                        .bind("category", p.getCategory_id())
                        .bind("kind", p.getKind())
                        .bind("stock", p.getStock())
                        .bind("thumbnail", p.getThumbnail())
                        .bind("description", p.getDescription())
                        .executeAndReturnGeneratedKeys("id")   // lấy id vừa tạo
                        .mapTo(Integer.class)
                        .one()
        );
    }


    public void deleteProduct(int id) {
        jdbi.useHandle(handle ->
                handle.createUpdate("DELETE FROM product WHERE id = :id")
                        .bind("id", id)
                        .execute()
        );
    }

    public void updateProduct(Product p) {
        jdbi.useHandle(handle ->
                handle.createUpdate("UPDATE product SET productName = :productName, price = :price, category_id = :category, " +
                                "kind = :kind, stock = :stock, thumbnail = :thumbnail, description = :description " +
                                "WHERE id = :id")
                        .bind("productName", p.getProductName())
                        .bind("price", p.getPrice())
                        .bind("category", p.getCategory_id())
                        .bind("kind", p.getKind())
                        .bind("stock", p.getStock())
                        .bind("thumbnail", p.getThumbnail())
                        .bind("description", p.getDescription())
                        .bind("id", p.getId())
                        .execute()
        );
    }

    public CartItem getProductForCart(int productId) {
        return jdbi.withHandle(handle -> {

            CartItem item = handle.createQuery(
                            "SELECT id, productName AS name, kind AS type, price,stock " +
                                    "FROM product WHERE id = :id")
                    .bind("id", productId)
                    .mapToBean(CartItem.class)
                    .findOne()
                    .orElse(null);

            if (item == null) return null;

            String image = handle.createQuery(
                            "SELECT image_url FROM product_image " +
                                    "WHERE product_id = :pid LIMIT 1")
                    .bind("pid", productId)
                    .mapTo(String.class)
                    .findOne()
                    .orElse(null);

            item.setImage(image);
            return item;
        });
    }
    public List<Category> getAllCategories() {
        return jdbi.withHandle(handle ->
                handle.createQuery(
                                "SELECT id, name, path_image FROM category"
                        )
                        .mapToBean(Category.class)
                        .list()
        );
    }
    public List<Product> findPageWithFilter(String keyword, String categoryId, String kind, int page, int pageSize ) {
        int offset = (page - 1) * pageSize;

        StringBuilder sql = new StringBuilder(" SELECT * FROM product WHERE 1=1 ");

        if (keyword != null && !keyword.isEmpty()) {
            sql.append(" AND ( CAST(id AS CHAR) LIKE :keyword OR productName LIKE :keyword ) ");
        }

        if (categoryId != null && !categoryId.isEmpty()) {
            sql.append(" AND category_id = :categoryId ");
        }

        if (kind != null && !kind.isEmpty()) {
            sql.append(" AND kind = :kind ");
        }

        sql.append(" ORDER BY id DESC LIMIT :limit OFFSET :offset");

        return jdbi.withHandle(handle -> {
            org.jdbi.v3.core.statement.Query query =
                    handle.createQuery(sql.toString());

            if (keyword != null && !keyword.isEmpty()) {
                query.bind("keyword", "%" + keyword + "%");
            }
            if (categoryId != null && !categoryId.isEmpty()) {
                query.bind("categoryId", Integer.parseInt(categoryId));
            }
            if (kind != null && !kind.isEmpty()) {
                query.bind("kind", kind);
            }

            query.bind("limit", pageSize);
            query.bind("offset", offset);

            return query.mapToBean(Product.class).list();
        });
    }

    public int countWithFilter(String keyword, String categoryId,String kind ) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM product WHERE 1=1");

        if (keyword != null && !keyword.isEmpty()) {
            sql.append(" AND ( CAST(id AS CHAR) LIKE :keyword OR productName LIKE :keyword)");
        }

        if (categoryId != null && !categoryId.isEmpty()) {
            sql.append(" AND category_id = :categoryId ");
        }

        if (kind != null && !kind.isEmpty()) {
            sql.append(" AND kind = :kind ");
        }

        return jdbi.withHandle(handle -> {
            org.jdbi.v3.core.statement.Query query =
                    handle.createQuery(sql.toString());

            if (keyword != null && !keyword.isEmpty()) {
                query.bind("keyword", "%" + keyword + "%");
            }
            if (categoryId != null && !categoryId.isEmpty()) {
                query.bind("categoryId", Integer.parseInt(categoryId));
            }
            if (kind != null && !kind.isEmpty()) {
                query.bind("kind", kind);
            }

            return query.mapTo(Integer.class).one();
        });
    }


    public boolean existsByName(String productName) {
        String sql = "SELECT COUNT(*) FROM product WHERE productName = :name";
        return jdbi.withHandle(handle ->
                handle.createQuery(sql)
                        .bind("name", productName)
                        .mapTo(Integer.class)
                        .one()
        ) > 0;
    }
    public boolean existsByNameExceptId(String productName, int productId) {
        String sql =" SELECT COUNT(*) FROM product WHERE productName = :name AND id <> :id ";
        return jdbi.withHandle(handle ->
                handle.createQuery(sql)
                        .bind("name", productName)
                        .bind("id", productId)
                        .mapTo(Integer.class)
                        .one() > 0
        );
    }

    public List<ProductImage> findByProductIds(List<Integer> productIds) {

        if (productIds == null || productIds.isEmpty()) {
            return new ArrayList<>();
        }

        return jdbi.withHandle(handle ->
                handle.createQuery(
                                "SELECT * FROM product_image WHERE product_id IN (<ids>)"
                        )
                        .bindList("ids", productIds)
                        .mapToBean(ProductImage.class)
                        .list()
        );
    }
    public void updateAfterCheckout(int productId, int quantity) {
        jdbi.useHandle(handle ->
                handle.createUpdate("UPDATE product SET stock = stock - :qty, soldQuantity = soldQuantity + :qty WHERE id = :id AND stock  >= :qty")
                        .bind("qty", quantity)
                        .bind("id", productId)
                        .execute()
        );
    }
    public void returnStockAfterCancel(int productId, int quantity) {
        String sql = "UPDATE product SET stock = stock + :qty, soldQuantity = soldQuantity - :qty WHERE id = :id";
        jdbi.useHandle(handle -> {
            handle.createUpdate(sql)
                    .bind("qty", quantity)
                    .bind("id", productId)
                    .execute();
        });
    }

}
