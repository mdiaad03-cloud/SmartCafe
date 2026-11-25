package com.smartcafe.smartcafe.service;
import org.springframework.stereotype.Service;
import com.smartcafe.smartcafe.model.Category;
import com.smartcafe.smartcafe.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import java.util.*;
@AllArgsConstructor 
@Service 
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override 
    public Category addCategory(Category category){
        return categoryRepository.save(category);
    }
    @Override 
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }
}
