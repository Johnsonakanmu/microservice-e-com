package com.ecommerce.product.mapper;

import com.ecommerce.product.dto.ProductResponse;
import com.ecommerce.product.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductResponse mapToProductResponse(Product product) {
        ProductResponse response = new ProductResponse();

        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setStockQuantity(product.getStockQuantity());
        response.setCategory(product.getCategory());
        response.setImageUrl(product.getImageUrl());
        response.setActive(product.getActive());

        return response;
    }

}
