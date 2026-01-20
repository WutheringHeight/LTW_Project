package com.Aviary.service;

import com.Aviary.components.Category;
import com.Aviary.dao.CategoryDAO;

import java.util.List;

//public class CategoryService {
//    CategoryDAO categoryDAO = new CategoryDAO();
//
//    public List<Category> getAllCategories() {
//        return categoryDAO.getAllCategories();
//    }
//    public void add(Category c) {
//        categoryDAO.addCategory(c);
//    }
//
//    public void update(Category c){
//        categoryDAO.updateCategory(c);
//    }
//
//    public void delete(int id) {
//        categoryDAO.deleteCategory(id);
//    }
//    public boolean existsByName(String name) {
//        return categoryDAO.existsByName(name);
//    }
//
//    public boolean existsByNameExceptId(String name, int id) {
//        return categoryDAO.existsByNameExceptId(name, id);
//    }
//}

public class CategoryService {
    private final CategoryDAO categoryDAO = new CategoryDAO();

    public List<Category> getAllCategories() {
        return categoryDAO.getAllCategories();
    }

    public void add(Category c) {
        validateName(c.getName());

        if (categoryDAO.existsByName(c.getName())) {
            throw new RuntimeException("Tên chủ đề đã tồn tại");
        }

        if (c.getPathImage() == null || c.getPathImage().isEmpty()) {
            throw new RuntimeException("Vui lòng chọn ảnh cho category");
        }

        categoryDAO.addCategory(c);
    }

    public void update(Category c) {
        validateName(c.getName());

        if (categoryDAO.existsByNameExceptId(c.getName(), c.getId())) {
            throw new RuntimeException("Tên chủ đề đã tồn tại");
        }

        categoryDAO.updateCategory(c);
    }

    public void delete(int id) {
        categoryDAO.deleteCategory(id);
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new RuntimeException("Tên category không được để trống");
        }

        if (name.length() > 50) {
            throw new RuntimeException("Tên category quá dài");
        }
    }

    public boolean existsByName(String name) {
        return categoryDAO.existsByName(name);
    }

    public boolean existsByNameExceptId(String name, int id) {
        return categoryDAO.existsByNameExceptId(name, id);
    }
}
