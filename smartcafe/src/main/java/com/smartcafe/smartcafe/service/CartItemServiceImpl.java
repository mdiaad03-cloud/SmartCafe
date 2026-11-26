package com.smartcafe.smartcafe.service;
import org.springframework.stereotype.Service;
import com.smartcafe.smartcafe.model.CartItem;
import com.smartcafe.smartcafe.model.MenuItem;
import com.smartcafe.smartcafe.model.TableSession;
import com.smartcafe.smartcafe.repository.CartItemRepository;
import com.smartcafe.smartcafe.repository.MenuItemRepository;
import com.smartcafe.smartcafe.repository.TableSessionRepository;
import lombok.AllArgsConstructor;
import java.util.*;
@Service 
@AllArgsConstructor 
public class CartItemServiceImpl implements CartItemService{
    private final CartItemRepository cartItemRepository;
    private final TableSessionRepository tableSessionRepository;
    private final MenuItemRepository menuItemRepository;
    @Override 
    public CartItem addCartItem(Integer sessionId,Integer menuItemId ,int quantity ,String note,String addedBy){
        TableSession tableSession= tableSessionRepository.findById(sessionId).orElseThrow(()->new RuntimeException("session not found"));
        if(!"ACTIVE".equals(tableSession.getStatus())){
            throw new RuntimeException("session is not active");
        }
        MenuItem menuItem= menuItemRepository.findById(menuItemId).orElseThrow(()->new RuntimeException("menu item not found"));
        CartItem cartItem=new CartItem();
        cartItem.setMenuItem(menuItem);
        cartItem.setAddedBy(addedBy);
        cartItem.setNote(note);
        cartItem.setQuantity(quantity);
        cartItem.setTableSession(tableSession);
        return cartItemRepository.save(cartItem);
    }
    @Override 
    public List<CartItem> getCart(Integer sessionId){
        return cartItemRepository.findByTableSessionId(sessionId);
    }
    @Override 
    public void removeItem(Integer itemId){
        cartItemRepository.deleteById(itemId);;
    }

}