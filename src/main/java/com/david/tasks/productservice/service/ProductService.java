package com.david.tasks.productservice.service;

import com.david.tasks.productservice.modal.Product;
import com.david.tasks.productservice.util.FilesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private Logger logger = LoggerFactory.getLogger(ProductService.class);

    private FilesUtil filesUtil;

    @Autowired
    public ProductService(FilesUtil filesUtil) {
        this.filesUtil = filesUtil;
    }

    public List<Product> getAllProducts() {
        logger.info("Listing all products...");
        return filesUtil.getProductList();
    }
}
