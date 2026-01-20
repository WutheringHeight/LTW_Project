package com.Aviary.dao;

import com.Aviary.components.Category;
import com.Aviary.components.JDBIProvider;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

public class CategoryDAO {

    private final Jdbi jdbi = JDBIProvider.get();

    public List<Category> getAllCategories() {
        return jdbi.withHandle(handle ->
            handle.createQuery("SELECT * FROM category")
                    .mapToBean(Category.class)
                    .list()

        );
    }

    public void addCategory(Category c) {
        jdbi.useHandle(handle ->
                handle.createUpdate(
                                "INSERT INTO category(name, path_image) VALUES (:name, :pathImage)"
                        )
                        .bindBean(c)
                        .execute()
        );
    }

    public void updateCategory(Category c) {
        jdbi.useHandle(handle -> {
            if (c.getPathImage() != null) {
                handle.createUpdate(
                        "UPDATE category SET name = :name, path_image = :pathImage WHERE id = :id"
                ).bindBean(c).execute();
            } else {
                handle.createUpdate(
                        "UPDATE category SET name = :name WHERE id = :id"
                ).bindBean(c).execute();
            }
        });
    }


    public void deleteCategory(int id) {
        jdbi.useHandle(handle ->
                handle.createUpdate("DELETE FROM category WHERE id = :id")
                        .bind("id", id)
                        .execute()
        );
    }

}
