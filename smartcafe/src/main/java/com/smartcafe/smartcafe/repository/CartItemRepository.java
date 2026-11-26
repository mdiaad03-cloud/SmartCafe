package com.smartcafe.smartcafe.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.smartcafe.smartcafe.model.CartItem;
import java.util.*;
public interface CartItemRepository extends JpaRepository<CartItem,Integer>{
    List<CartItem>findByTableSessionId(Integer sessionid); 
}
