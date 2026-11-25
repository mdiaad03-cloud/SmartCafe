package com.smartcafe.smartcafe.service;
import com.smartcafe.smartcafe.model.MenuItem;
import java.util.*;
public interface MenuItemService {
    MenuItem addnewItem(MenuItem menuItem);
    List<MenuItem> getAllMenuitem();
    List <MenuItem> getMenuItemsByCategoryid(Integer categoryid);
}
