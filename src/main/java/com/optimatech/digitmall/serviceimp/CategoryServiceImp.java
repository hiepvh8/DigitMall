package com.optimatech.digitmall.serviceimp;

import com.optimatech.digitmall.model.entity.Category;
import com.optimatech.digitmall.repository.CategoryRepository;
import com.optimatech.digitmall.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImp implements CategoryService {
    @Autowired
    private final CategoryRepository categoryRepository;

    public CategoryServiceImp(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    //Return list product
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    //Return product by id
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    //Create product
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    //Update product
    public Category updateCategory(Category category) {
        return categoryRepository.save(category);
    }

    //Delete product by id
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
