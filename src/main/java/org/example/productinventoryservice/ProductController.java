package org.example.productinventoryservice;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        // Send the JSON of product to the Service
        return productService.createProduct(product);
    }

    @PostMapping("/{id}/reduce-stock")
    public Product reduceStock(@PathVariable("id") Long id, @RequestParam int amount) {
        return productService.updateStock(id, amount);
    }
}
