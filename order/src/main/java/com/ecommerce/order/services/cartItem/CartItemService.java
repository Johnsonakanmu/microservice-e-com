package com.ecommerce.order.services.cartItem;

import com.ecommerce.order.dto.CartItemRequest;
import com.ecommerce.order.model.CartItem;
import com.ecommerce.order.record.CartItemResponse;

import java.util.List;

public interface CartItemService {

    public CartItemResponse addToCart(String userId, CartItemRequest request);

//    boolean deleteItemFromCart(String userId, Long productId);


    boolean deleteItemFromCart(String userId, String productId);

    public List<CartItem> getCart(String UserId);

    void clearCart(String userId);
}
