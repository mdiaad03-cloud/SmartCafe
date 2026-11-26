package com.smartcafe.smartcafe.repository;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import com.smartcafe.smartcafe.model.OrderItem;
public interface OrderItemRepository extends JpaRepository<OrderItem,Integer>{
    List<OrderItem> findByOrderId(Integer orderId);
}