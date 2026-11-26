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
import com.smartcafe.smartcafe.model.CartItem;
import com.smartcafe.smartcafe.model.MenuItem;
import com.smartcafe.smartcafe.model.TableSession;
import com.smartcafe.smartcafe.repository.CartItemRepository;
import com.smartcafe.smartcafe.repository.MenuItemRepository;
import com.smartcafe.smartcafe.repository.TableSessionRepository;
@ExtendWith(MockitoExtension.class)
public class CartItemServiceImplTest {
    @Mock
    private CartItemRepository cartItemRepository;
    @Mock
    private TableSessionRepository tableSessionRepository;
    @Mock
    private MenuItemRepository menuItemRepository;
    @InjectMocks
    private CartItemServiceImpl cartItemService;
    @Test
    void addCartItem_savesItem_whenSessionIsActive(){
        TableSession tableSession=new TableSession();
        tableSession.setId(1);
        tableSession.setStatus("ACTIVE");
        MenuItem menuItem=new MenuItem();
        menuItem.setId(2);
        menuItem.setPrice(45.5);
        when(tableSessionRepository.findById(1)).thenReturn(Optional.of(tableSession));
        when(menuItemRepository.findById(2)).thenReturn(Optional.of(menuItem));
        when(cartItemRepository.save(any(CartItem.class))).thenAnswer(inv->inv.getArgument(0));
        CartItem result=cartItemService.addCartItem(1, 2, 2, "no sugar", "Ahmed");
        assertEquals(2, result.getQuantity());
        assertEquals("no sugar", result.getNote());
        assertEquals("Ahmed", result.getAddedBy());
        assertEquals(tableSession, result.getTableSession());
        assertEquals(menuItem, result.getMenuItem());
        verify(cartItemRepository).save(any(CartItem.class));
    }
    @Test
    void addCartItem_rejectsEndedSession(){
        TableSession tableSession=new TableSession();
        tableSession.setId(1);
        tableSession.setStatus("ENDED");
        when(tableSessionRepository.findById(1)).thenReturn(Optional.of(tableSession));
        RuntimeException ex=assertThrows(RuntimeException.class, ()->cartItemService.addCartItem(1, 2, 1, null, "Ahmed"));
        assertEquals("session is not active", ex.getMessage());
        verify(cartItemRepository, never()).save(any());
    }
    @Test
    void getCart_returnsSessionItems(){
        List<CartItem> items=List.of(new CartItem());
        when(cartItemRepository.findByTableSessionId(1)).thenReturn(items);
        List<CartItem> result=cartItemService.getCart(1);
        assertEquals(1, result.size());
    }
    @Test
    void removeItem_deletesById(){
        cartItemService.removeItem(5);
        verify(cartItemRepository).deleteById(5);
    }
}