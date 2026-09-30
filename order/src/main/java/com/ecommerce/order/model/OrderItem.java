package com.ecommerce.order.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "orderItems")
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productId;
    private Integer quantity;
    private BigDecimal price;
    @ManyToOne()
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}
