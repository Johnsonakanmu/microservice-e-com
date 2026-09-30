package com.ecommerce.order.dto;

import com.ecommerce.order.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderResponse {

    private Long id;
    private BigDecimal totalAmount;
    private OrderStatus status;
    private List<OrderItemDto> items;
    private LocalDateTime createdAt;


}
