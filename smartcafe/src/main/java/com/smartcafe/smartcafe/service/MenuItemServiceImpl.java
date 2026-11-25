package com.smartcafe.smartcafe.service;
import org.springframework.stereotype.Service;
import java.util.*;
import com.smartcafe.smartcafe.model.MenuItem;
import com.smartcafe.smartcafe.repository.MenuItemRepository;
import lombok.AllArgsConstructor;
@Service 
@AllArgsConstructor 
public class MenuItemServiceImpl implements  MenuItemService{
    private MenuItemRepository menuItemRepository;
    @Override
    public MenuItem addnewItem(MenuItem menuItem){
        return menuItemRepository.save(menuItem);
    } 
    @Override 
    public List<MenuItem> getAllMenuitem (){
        return menuItemRepository.findAll();
    }
    @Override 
    public List<MenuItem>getMenuItemsByCategoryid(Integer categoryid){
        return menuItemRepository.findByCategoryId(categoryid);
    }
}