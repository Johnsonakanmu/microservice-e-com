package com.ecommerce.order.controller;

import com.ecommerce.order.dto.OrderResponse;
import com.ecommerce.order.record.ApiResponse;
import com.ecommerce.order.services.order.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;


    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponse>> createOrder(
            @RequestHeader("X-USER-ID") Long userId
    ) {

        OrderResponse orderResponse = orderService.createOrder(userId);

        ApiResponse<OrderResponse> response =
                new ApiResponse<>(
                        true,
                        "Order created successfully",
                        orderResponse
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

}
