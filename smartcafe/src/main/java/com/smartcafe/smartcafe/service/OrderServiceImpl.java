package com.smartcafe.smartcafe.service;
import java.time.LocalDateTime;
import java.util.*;
import com.smartcafe.smartcafe.model.CartItem;
import com.smartcafe.smartcafe.model.Order;
import com.smartcafe.smartcafe.model.OrderItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.smartcafe.smartcafe.repository.OrderItemRepository;
import com.smartcafe.smartcafe.repository.OrderRepository;
import com.smartcafe.smartcafe.repository.TableSessionRepository;
import com.smartcafe.smartcafe.repository.CartItemRepository;
import com.smartcafe.smartcafe.model.TableSession;
import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor  
public class OrderServiceImpl implements OrderService{
    private OrderRepository orderRepository;
    private TableSessionRepository tableSessionRepository;
    private OrderItemRepository orderItemRepository;
    private CartItemRepository cartItemRepository;
    @Override 
    @Transactional
    public Order createOrder(Integer tableId){
        TableSession tableSession=tableSessionRepository.findByCafeTableIdAndStatus(tableId, "ACTIVE").orElseThrow(()->new RuntimeException("session not found"));
        List<CartItem> cartItems=cartItemRepository.findByTableSessionId(tableSession.getId());
        if(cartItems.isEmpty()){
            throw new RuntimeException("cart is empty");
        }
        double total=0;
        for(CartItem cartItem:cartItems){
            total+=cartItem.getQuantity()*cartItem.getMenuItem().getPrice();
        }
        Order order=new Order();
        order.setTableSession(tableSession);
        order.setStatus("PENDING");
        order.setCreatedAt(LocalDateTime.now());
        order.setTotal(total);
        Order savedOrder=orderRepository.save(order);
        for(CartItem cartItem:cartItems){
            OrderItem orderItem=new OrderItem();
            orderItem.setOrder(savedOrder);
            orderItem.setMenuItem(cartItem.getMenuItem());
            orderItem.setQuantity(cartItem.getQuantity());
            double unitPrice=cartItem.getMenuItem().getPrice();
            orderItem.setUnitPrice(unitPrice);
            orderItemRepository.save(orderItem);
        }
        cartItemRepository.deleteAll(cartItems);
        return savedOrder;
    }
    @Override
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }
    @Override
    public List<OrderItem> getOrderItems(Integer orderId){
        return orderItemRepository.findByOrderId(orderId);
    }
    @Override
    public Order updateOrderStatus(Integer orderId, String status){
        if(!"PENDING".equals(status) && !"PREPARING".equals(status) && !"READY".equals(status)){
            throw new RuntimeException("invalid status");
        }
        Order order=orderRepository.findById(orderId).orElseThrow(()->new RuntimeException("order not found"));
        order.setStatus(status);
        return orderRepository.save(order);
    }
}