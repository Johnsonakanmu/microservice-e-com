package com.ecommerce.product.services.product;

import com.ecommerce.product.dto.ProductRequest;
import com.ecommerce.product.dto.ProductResponse;
import com.ecommerce.product.mapper.ProductMapper;
import com.ecommerce.product.model.Product;
import com.ecommerce.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;
    private ProductMapper productMapper;

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {

        Product product = new Product();
        updateProductFromRequest(product, productRequest);
        Product savedProduct = productRepository.save(product);
        return productMapper.mapToProductResponse(savedProduct);
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    updateProductFromRequest(existingProduct, request);
                    Product savedProduct = productRepository.save(existingProduct);
                    return productMapper.mapToProductResponse(savedProduct);
                }).orElseThrow(() -> new RuntimeException("Product not found " + id));
    }

    @Override
    public List<ProductResponse> getAllProducts() {

        return productRepository.findByActiveTrue().stream()
                .map(productMapper::mapToProductResponse)
                .toList();
    }

    @Override
    public ProductResponse getProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Product not found")
        );

        return productMapper.mapToProductResponse(product);
    }

    @Override
    public void deleteProduct(Long id) {

       Product product =  productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Product not found")
        );
       product.setActive(false);

        productRepository.save(product);

    }

    @Override
    public List<ProductResponse> searchProducts(String keyword) {

       return productRepository.searchProduct(keyword).stream()
                .map(productMapper::mapToProductResponse)
                .toList();
    }

    private  void updateProductFromRequest(Product product, ProductRequest request) {
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStockQuantity(request.getStockQuantity());
        product.setCategory(request.getCategory());
        product.setImageUrl(request.getImageUrl());
    }

}
