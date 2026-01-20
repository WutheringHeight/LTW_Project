package com.Aviary.dao;

import com.Aviary.components.JDBIProvider;
import com.Aviary.components.ProductImage;
import org.jdbi.v3.core.Jdbi;

public class ProductImageDAO {

    private final Jdbi jdbi = JDBIProvider.get();

    public ProductImageDAO() {
    }

    public void addProductImage(ProductImage pi) {
        String sql = " INSERT INTO product_image (product_id, image_url, image_type) VALUES (:pid, :url, :type) ";

        jdbi.useHandle(h ->
                h.createUpdate(sql)
                        .bind("pid", pi.getProductId())
                        .bind("url", pi.getImageUrl())
                        .bind("type", pi.getType())
                        .execute()
        );
    }

    public void deleteByProductIdAndType(int productId, String type) {
        String sql = " DELETE FROM product_image WHERE product_id = :pid AND image_type = :type ";

        jdbi.useHandle(h ->
                h.createUpdate(sql)
                        .bind("pid", productId)
                        .bind("type", type)
                        .execute()
        );
    }

}
