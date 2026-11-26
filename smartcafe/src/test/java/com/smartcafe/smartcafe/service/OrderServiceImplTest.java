package com.smartcafe.smartcafe.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import java.util.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.smartcafe.smartcafe.model.CartItem;
import com.smartcafe.smartcafe.model.MenuItem;
import com.smartcafe.smartcafe.model.Order;
import com.smartcafe.smartcafe.model.OrderItem;
import com.smartcafe.smartcafe.model.TableSession;
import com.smartcafe.smartcafe.repository.CartItemRepository;
import com.smartcafe.smartcafe.repository.OrderItemRepository;
import com.smartcafe.smartcafe.repository.OrderRepository;
import com.smartcafe.smartcafe.repository.TableSessionRepository;
@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {
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
    void createOrder_createsOrderAndItemsAndClearsCart(){
        TableSession tableSession=new TableSession();
        tableSession.setId(10);
        tableSession.setStatus("ACTIVE");
        MenuItem latte=new MenuItem();
        latte.setId(1);
        latte.setPrice(45.5);
        MenuItem tea=new MenuItem();
        tea.setId(2);
        tea.setPrice(20.0);
        CartItem item1=new CartItem();
        item1.setQuantity(2);
        item1.setMenuItem(latte);
        item1.setTableSession(tableSession);
        CartItem item2=new CartItem();
        item2.setQuantity(1);
        item2.setMenuItem(tea);
        item2.setTableSession(tableSession);
        List<CartItem> cartItems=List.of(item1, item2);
        when(tableSessionRepository.findByCafeTableIdAndStatus(1, "ACTIVE")).thenReturn(Optional.of(tableSession));
        when(cartItemRepository.findByTableSessionId(10)).thenReturn(cartItems);
        when(orderRepository.save(any(Order.class))).thenAnswer(inv->inv.getArgument(0));
        Order result=orderService.createOrder(1);
        assertEquals("PENDING", result.getStatus());
        assertEquals(111.0, result.getTotal(), 0.001);
        assertNotNull(result.getCreatedAt());
        assertEquals(tableSession, result.getTableSession());
        ArgumentCaptor<OrderItem> captor=ArgumentCaptor.forClass(OrderItem.class);
        verify(orderItemRepository, times(2)).save(captor.capture());
        List<OrderItem> savedItems=captor.getAllValues();
        assertEquals(2, savedItems.get(0).getQuantity());
        assertEquals(45.5, savedItems.get(0).getUnitPrice());
        assertEquals(1, savedItems.get(1).getQuantity());
        assertEquals(20.0, savedItems.get(1).getUnitPrice());
        verify(cartItemRepository).deleteAll(cartItems);
    }
    @Test
    void createOrder_throws_whenCartIsEmpty(){
        TableSession tableSession=new TableSession();
        tableSession.setId(10);
        when(tableSessionRepository.findByCafeTableIdAndStatus(1, "ACTIVE")).thenReturn(Optional.of(tableSession));
        when(cartItemRepository.findByTableSessionId(10)).thenReturn(List.of());
        RuntimeException ex=assertThrows(RuntimeException.class, ()->orderService.createOrder(1));
        assertEquals("cart is empty", ex.getMessage());
        verify(orderRepository, never()).save(any());
        verify(cartItemRepository, never()).deleteAll(any());
    }
    @Test
    void createOrder_throws_whenNoActiveSession(){
        when(tableSessionRepository.findByCafeTableIdAndStatus(1, "ACTIVE")).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, ()->orderService.createOrder(1));
    }
}