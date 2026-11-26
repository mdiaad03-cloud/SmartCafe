package com.smartcafe.smartcafe.service;
import java.util.*;
import com.smartcafe.smartcafe.model.CartItem;
public interface CartItemService {
    CartItem addCartItem(Integer sessionId,Integer menuItemId ,int quantity ,String note,String addedBy);
    List<CartItem>getCart(Integer sessionId);
    void removeItem(Integer itemId);
}