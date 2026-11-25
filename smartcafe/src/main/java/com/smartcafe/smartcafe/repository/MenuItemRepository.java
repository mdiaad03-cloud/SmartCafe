package com.smartcafe.smartcafe.repository;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import com.smartcafe.smartcafe.model.MenuItem;
public interface MenuItemRepository extends JpaRepository<MenuItem,Integer> {
    List <MenuItem> findByCategoryId(Integer categoryid);
}
