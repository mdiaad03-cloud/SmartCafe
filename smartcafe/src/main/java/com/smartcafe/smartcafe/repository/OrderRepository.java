package com.smartcafe.smartcafe.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.smartcafe.smartcafe.model.Order;
public interface OrderRepository extends JpaRepository<Order,Integer>{
}
