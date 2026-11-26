package com.smartcafe.smartcafe.controller;
import java.util.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.smartcafe.smartcafe.model.Order;
import com.smartcafe.smartcafe.model.OrderItem;
import com.smartcafe.smartcafe.service.OrderService;
import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping("/create/{tableId}")
    public Order createOrder(@PathVariable Integer tableId){
        return orderService.createOrder(tableId);
    }
    @GetMapping
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }
    @GetMapping("/{orderId}/items")
    public List<OrderItem> getOrderItems(@PathVariable Integer orderId){
        return orderService.getOrderItems(orderId);
    }
    @PutMapping("/{orderId}/status")
    public Order updateOrderStatus(@PathVariable Integer orderId, @RequestParam String status){
        return orderService.updateOrderStatus(orderId, status);
    }
}