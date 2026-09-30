package com.ecommerce.order.services.order;

import com.ecommerce.order.dto.OrderResponse;

public interface OrderService {

    public OrderResponse createOrder(Long userId);
}
