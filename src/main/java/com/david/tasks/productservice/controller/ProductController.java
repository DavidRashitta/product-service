package com.david.tasks.productservice.controller;

import com.david.tasks.productservice.exception.ProductNotFoundException;
import com.david.tasks.productservice.modal.Product;
import com.david.tasks.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getAllProducts")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/getSingleProduct/{sku}")
    public Product getSingleProduct(@PathVariable String sku) {
        Product product = productService.getSingleProduct(sku);
        if (product == null) {
            throw new ProductNotFoundException("Cannot find SKU: ", sku);
        }
        return product;
    }
}
