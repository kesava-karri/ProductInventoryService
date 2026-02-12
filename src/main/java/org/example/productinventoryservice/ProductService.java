package org.example.productinventoryservice;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        if (product == null || product.getProductName() == null) {
            throw new IllegalArgumentException("Product name must not be null");
        }

        return productRepository.save(product);
    }
}
