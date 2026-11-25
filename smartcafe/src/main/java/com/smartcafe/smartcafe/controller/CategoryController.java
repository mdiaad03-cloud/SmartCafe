package com.smartcafe.smartcafe.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController; 
import com.smartcafe.smartcafe.model.Category;
import com.smartcafe.smartcafe.service.CategoryService;
import lombok.AllArgsConstructor;
import java.util.*;
@AllArgsConstructor 
@RestController 
@RequestMapping ("api/category")
public class CategoryController {
    private CategoryService categoryService;
    @GetMapping("/getAllCategories")
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }
    @PostMapping ("/addCategory")
    public Category addCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }
    
}
