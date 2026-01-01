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
}
