package com.david.tasks.productservice.controller;

import com.david.tasks.productservice.exception.ProductNotFoundException;
import com.david.tasks.productservice.exception.ProductUnitNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ProductControllerTest {

    private ProductController productController;

    @Autowired
    public ProductControllerTest(ProductController productController) {
        this.productController = productController;
    }


    @Test
    void getSingleProductNotFound() {
        assertThrows(ProductNotFoundException.class, () -> productController.getSingleProduct("NOT_EXIST"));
    }

    @Test
    void getPriceOfProduct() {
        assertThrows(ProductUnitNotFoundException.class, () -> productController.getPriceOfProduct("NOT_EXIST", "NOT_EXIST"));
    }
}