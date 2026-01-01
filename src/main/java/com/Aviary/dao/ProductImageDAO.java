package com.Aviary.dao;

import com.Aviary.components.JDBIProvider;
import com.Aviary.components.ProductImage;
import org.jdbi.v3.core.Jdbi;

public class ProductImageDAO {

    private final Jdbi jdbi = JDBIProvider.get();

    public ProductImageDAO() {
    }

    public void addProductImage(ProductImage pi) {
        jdbi.useHandle(handle ->
                handle.createUpdate("INSERT INTO product_image (product_id, image_url) VALUES (:productId, :imageUrl)")
                        .bind("productId", pi.getProductId())
                        .bind("imageUrl", pi.getImageUrl())
                        .execute()
        );
    }

}
