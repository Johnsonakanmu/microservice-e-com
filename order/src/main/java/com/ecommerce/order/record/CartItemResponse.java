package com.ecommerce.order.record;


import java.math.BigDecimal;

public record CartItemResponse(
        Long id,
        Integer quantity,
        BigDecimal price
) {
}
