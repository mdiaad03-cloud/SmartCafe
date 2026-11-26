package com.smartcafe.smartcafe.controller;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.smartcafe.smartcafe.model.CartItem;
import com.smartcafe.smartcafe.service.CartItemService;
import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/api/cart")
@AllArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    @PostMapping("/add/{sessionId}/{menuItemId}")
    public CartItem addCartItem(@PathVariable Integer sessionId,
                                @PathVariable Integer menuItemId,
                                @RequestParam int quantity,
                                @RequestParam(required = false) String note,
                                @RequestParam String addedBy) {
        return cartItemService.addCartItem(sessionId, menuItemId, quantity, note, addedBy);
    }

    @GetMapping("/{sessionId}")
    public List<CartItem> getCart(@PathVariable Integer sessionId) {
        return cartItemService.getCart(sessionId);
    }

    @DeleteMapping("/remove/{itemId}")
    public void removeItem(@PathVariable Integer itemId) {
        cartItemService.removeItem(itemId);
    }
}