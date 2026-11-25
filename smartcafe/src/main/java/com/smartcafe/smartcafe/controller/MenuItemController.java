package com.smartcafe.smartcafe.controller;
import java.util.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.smartcafe.smartcafe.model.MenuItem;
import com.smartcafe.smartcafe.service.MenuItemService;
import lombok.AllArgsConstructor;
@RestController 
@AllArgsConstructor 
@RequestMapping ("/api/item")
public class MenuItemController {
    private MenuItemService menuItemService;
    @PostMapping ("/addnewItem")
    public MenuItem addMenuItem(@RequestBody  MenuItem menuItem){
        return menuItemService.addnewItem(menuItem);
    }
    @GetMapping("/getAllCategories")
    public List<MenuItem> gMenuItems(){
        return menuItemService.getAllMenuitem();
    }
    @GetMapping ("category/{categoryid}")
    public List<MenuItem> getMenuItemsByCategoryid(@PathVariable  Integer categoryid){
        return menuItemService.getMenuItemsByCategoryid(categoryid);
    }
    
}
