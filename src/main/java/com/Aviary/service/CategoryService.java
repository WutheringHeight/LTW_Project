package com.Aviary.service;

import com.Aviary.components.Category;
import com.Aviary.dao.CategoryDAO;

import java.util.List;

public class CategoryService {
    CategoryDAO categoryDAO = new CategoryDAO();
    public List<Category> getAllCategories() {return categoryDAO.getAllCategories();}
}
