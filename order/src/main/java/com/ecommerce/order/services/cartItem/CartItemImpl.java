package com.ecommerce.order.services.cartItem;

import com.ecommerce.order.dto.CartItemRequest;
import com.ecommerce.order.model.CartItem;
import com.ecommerce.order.record.CartItemResponse;
import com.ecommerce.order.repository.CartItemRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class CartItemImpl implements CartItemService{

    private final CartItemRepository cartItemRepository;


    @Override
    public CartItemResponse addToCart(
            String userId,
            CartItemRequest request) {

        //        Product product = productRepository.findById(request.getProductId())
//                .orElseThrow(() ->
//                        new RuntimeException("Product not found"));
//
//        User user = userRepository.findById(Long.valueOf(userId))
//                .orElseThrow(() ->
//                        new RuntimeException("User not found"));

        CartItem existingCartItem =
                cartItemRepository.findByUserIdAndProductId(
                        userId,
                        request.getProductId()
                );

        CartItem cartItem;

        if (existingCartItem != null) {

            // Add the new quantity to the existing quantity
            int newQuantity =
                    existingCartItem.getQuantity()
                            + request.getQuantity();

            existingCartItem.setQuantity(newQuantity);

            // For now, using a fixed product price
            BigDecimal price = BigDecimal.valueOf(1000.00);

            BigDecimal totalPrice =
                    price.multiply(BigDecimal.valueOf(newQuantity));

            existingCartItem.setPrice(totalPrice);

            cartItem = existingCartItem;

        } else {

            cartItem = new CartItem();

            cartItem.setUserId(userId);
            cartItem.setProductId(request.getProductId());
            cartItem.setQuantity(request.getQuantity());

            // For now, using a fixed product price
            BigDecimal price = BigDecimal.valueOf(1000.00);

            BigDecimal totalPrice =
                    price.multiply(
                            BigDecimal.valueOf(request.getQuantity())
                    );

            cartItem.setPrice(totalPrice);
        }

        CartItem savedCartItem =
                cartItemRepository.save(cartItem);

        return new CartItemResponse(
                savedCartItem.getId(),
                savedCartItem.getQuantity(),
                savedCartItem.getPrice()
        );
    }

    @Override
    public boolean deleteItemFromCart(String userId, String productId) {

        if (!cartItemRepository.existsByUserIdAndProductId(userId, productId)) {
            return false;
        }

        cartItemRepository.deleteByUserIdAndProductId(userId, productId);
        return true;
    }

    @Override
    public List<CartItem> getCart(String userId) {

       return cartItemRepository.findByUserId(userId);
    }

    @Override
    public void clearCart(String userId) {
        cartItemRepository.deleteByUserId(userId);
    }


}
