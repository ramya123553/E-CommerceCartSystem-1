package com.example.ecommerce.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.dao.CategoryDao;
import com.example.ecommerce.models.Category;

import java.util.List;


@Service
public class CategoryService {

    @Autowired
    private CategoryDao categoryDao; // Updated field name to follow Java naming conventions

    public Category addCategory(String name) {
        return this.categoryDao.addCategory(name);
    }

    public List<Category> getCategories() {
        return this.categoryDao.getCategories();
    }

    public Boolean deleteCategory(int id) {
        return this.categoryDao.deleteCategory(id); // Updated method name
    }

    public Category updateCategory(int id, String name) {
        return this.categoryDao.updateCategory(id, name);
    }

    public Category getCategory(int id) {
        return this.categoryDao.getCategory(id);
    }
}
