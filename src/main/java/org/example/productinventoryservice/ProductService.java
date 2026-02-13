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

    public Product updateStock(Long productId, int quantityToSubtract) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found!"));

        if (product.getStockQuantity() - quantityToSubtract < 0) {
            throw new RuntimeException("Not enough stock for: " + product.getProductName());
        }

        int newStock = product.getStockQuantity() - quantityToSubtract;
        product.setStockQuantity(newStock);

        return productRepository.save(product);
    }
}
