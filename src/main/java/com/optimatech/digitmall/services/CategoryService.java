package com.optimatech.digitmall.services;

import com.optimatech.digitmall.model.entity.Category;
import com.optimatech.digitmall.model.entity.Product;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    public List<Category> getAllCategories();
    public Optional<Category> getCategoryById(Long id);
    public Category createCategory(Category category);
    public Category updateCategory(Category category);
    public void deleteCategory(Long id);
}
