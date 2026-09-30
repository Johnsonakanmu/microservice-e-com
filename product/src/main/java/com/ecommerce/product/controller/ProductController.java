package com.ecommerce.product.controller;

import com.ecommerce.product.dto.ProductRequest;
import com.ecommerce.product.dto.ProductResponse;
import com.ecommerce.product.services.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) {

        ProductResponse response = productService.createProduct(productRequest);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable() Long id,
                                                         @RequestBody ProductRequest productRequest) {

        ProductResponse response = productService.updateProduct(id, productRequest);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable() Long id) {

        return ResponseEntity.ok(productService.getProduct(id));
    }


    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts() {

        return ResponseEntity.ok(productService.getAllProducts());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> DelectProduct(@PathVariable() Long id) {

        productService.deleteProduct(id);

        return ResponseEntity.ok("Product Deleted successfully");
    }


    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> searchProducts(@RequestParam String keyword) {

        return ResponseEntity.ok(productService.searchProducts(keyword));
    }

}
