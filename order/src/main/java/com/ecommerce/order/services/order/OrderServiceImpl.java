package com.ecommerce.order.services.order;

import com.ecommerce.order.dto.OrderResponse;
import com.ecommerce.order.mapper.OrderMapper;
import com.ecommerce.order.model.*;
import com.ecommerce.order.repository.OrderRepository;
import com.ecommerce.order.services.cartItem.CartItemService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService{
    private OrderRepository orderRepository;
    private final CartItemService cartItemService;
    private final OrderMapper orderMapper;

    @Override
    @Transactional
    public OrderResponse createOrder(Long userId) {

        // Validate cart
        List<CartItem> cartItems = cartItemService.getCart(String.valueOf(userId));

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        // Validate user
//        User user = userRepository.findById(Long.valueOf(userId))
//                .orElseThrow(() -> new RuntimeException("User not found"));

        // Calculate total price
        BigDecimal totalPrice = cartItems.stream()
                .map(item ->
                        item.getPrice()
                                .multiply(
                                        BigDecimal.valueOf(item.getQuantity())
                                )
                )
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Create Order
        Order order = new Order();

        order.setUserId(userId);
        order.setStatus(OrderStatus.CONFORMED);
        order.setTotalAmount(totalPrice);

        // Create Order Items
        List<OrderItem> orderItems = cartItems.stream()
                .map(item -> new OrderItem(
                        null,
                        item.getProductId(),
                        item.getQuantity(),
                        item.getPrice(),
                        order
                ))
                .collect(Collectors.toList());

        order.setItems(orderItems);

        // Save Order
        Order savedOrder = orderRepository.save(order);

        // Clear cart
        cartItemService.clearCart(String.valueOf(userId));

        // Return response
        return orderMapper.mapToOrderResponse(savedOrder);
    }
}
