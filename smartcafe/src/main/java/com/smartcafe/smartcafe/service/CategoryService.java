package com.smartcafe.smartcafe.service;
import com.smartcafe.smartcafe.model.Category;
import java.util.*;
public interface CategoryService {
    Category addCategory(Category category);
    List <Category> getAllCategories();
    
}
