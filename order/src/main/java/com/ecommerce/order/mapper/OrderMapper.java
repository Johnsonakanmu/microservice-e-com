package com.ecommerce.order.mapper;

import com.ecommerce.order.dto.OrderItemDto;
import com.ecommerce.order.dto.OrderResponse;
import com.ecommerce.order.model.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OrderMapper {

    public OrderResponse mapToOrderResponse(Order order) {

        return new OrderResponse(
                order.getId(),
                order.getTotalAmount(),
                order.getStatus(),
                order.getItems()
                        .stream()
                        .map(orderItem -> new OrderItemDto(
                                orderItem.getId(),
                                orderItem.getProductId(),
                                orderItem.getQuantity(),
                                orderItem.getPrice(),
                                orderItem.getPrice().multiply(new BigDecimal(orderItem.getId()))
                        ))
                        .toList(),
                order.getCreatedAt()

        );

    }

    }
