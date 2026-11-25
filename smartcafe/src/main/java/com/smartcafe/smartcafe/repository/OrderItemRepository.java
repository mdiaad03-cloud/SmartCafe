package com.smartcafe.smartcafe.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.smartcafe.smartcafe.model.OrderItem;
public interface OrderItemRepository extends JpaRepository<OrderItem,Integer>{
}
