package com.ecommerce.order.record;

public record ApiResponse<T>(
        boolean success,
        String message,
        T data
) {
}