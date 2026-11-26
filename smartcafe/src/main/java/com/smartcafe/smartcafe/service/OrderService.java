package com.smartcafe.smartcafe.service;
import java.util.*;
import com.smartcafe.smartcafe.model.Order;
import com.smartcafe.smartcafe.model.OrderItem;
public interface OrderService {
    Order createOrder(Integer tableId);
    List<Order> getAllOrders();
    List<OrderItem> getOrderItems(Integer orderId);
    Order updateOrderStatus(Integer orderId, String status);
}