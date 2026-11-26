package com.smartcafe.smartcafe.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import java.util.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.smartcafe.smartcafe.model.Order;
import com.smartcafe.smartcafe.model.OrderItem;
import com.smartcafe.smartcafe.repository.CartItemRepository;
import com.smartcafe.smartcafe.repository.OrderItemRepository;
import com.smartcafe.smartcafe.repository.OrderRepository;
import com.smartcafe.smartcafe.repository.TableSessionRepository;
@ExtendWith(MockitoExtension.class)
public class OrderServiceCafeMethodsTest {
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private TableSessionRepository tableSessionRepository;
    @Mock
    private OrderItemRepository orderItemRepository;
    @Mock
    private CartItemRepository cartItemRepository;
    @InjectMocks
    private OrderServiceImpl orderService;
    @Test
    void getAllOrders_returnsAllOrders(){
        List<Order> orders=List.of(new Order(), new Order());
        when(orderRepository.findAll()).thenReturn(orders);
        List<Order> result=orderService.getAllOrders();
        assertEquals(2, result.size());
        verify(orderRepository).findAll();
    }
    @Test
    void getOrderItems_returnsItemsForOrder(){
        List<OrderItem> items=List.of(new OrderItem());
        when(orderItemRepository.findByOrderId(1)).thenReturn(items);
        List<OrderItem> result=orderService.getOrderItems(1);
        assertEquals(1, result.size());
        verify(orderItemRepository).findByOrderId(1);
    }
    @Test
    void updateOrderStatus_updatesToPreparing(){
        Order order=new Order();
        order.setId(1);
        order.setStatus("PENDING");
        when(orderRepository.findById(1)).thenReturn(Optional.of(order));
        when(orderRepository.save(any(Order.class))).thenAnswer(inv->inv.getArgument(0));
        Order result=orderService.updateOrderStatus(1, "PREPARING");
        assertEquals("PREPARING", result.getStatus());
        verify(orderRepository).save(order);
    }
    @Test
    void updateOrderStatus_throws_whenInvalidStatus(){
        RuntimeException ex=assertThrows(RuntimeException.class, ()->orderService.updateOrderStatus(1, "CANCELLED"));
        assertEquals("invalid status", ex.getMessage());
        verify(orderRepository, never()).save(any());
    }
}