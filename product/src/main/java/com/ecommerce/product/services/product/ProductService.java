package com.ecommerce.product.services.product;

import com.ecommerce.product.dto.ProductRequest;
import com.ecommerce.product.dto.ProductResponse;

import java.util.List;

public interface ProductService {

    public ProductResponse createProduct(ProductRequest productRequest);

    public ProductResponse updateProduct(Long id, ProductRequest request);

    public List<ProductResponse> getAllProducts();
    public ProductResponse getProduct(Long id);

    void deleteProduct(Long id);

    public List<ProductResponse> searchProducts(String keyword);

}
