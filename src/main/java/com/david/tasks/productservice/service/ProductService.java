package com.david.tasks.productservice.service;

import com.david.tasks.productservice.modal.Price;
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

    public Product getSingleProduct(String sku) {
        logger.info("Finding product with SKU " + sku);
        List<Product> products = filesUtil.getProductList();
        for (Product product : products) {
            if (product.getSKU().equals(sku)) {
                return product;
            }
        }
        return null;
    }

    public Double getPriceOfProduct(String sku, String unit) {
        Product selectedSKU = getSingleProduct(sku);
        logger.info("Finding price for product " + sku + " with unit " + unit);

        if (selectedSKU != null && selectedSKU.getPriceList() != null) {

            for (Price price : selectedSKU.getPriceList()) {
                if (price.getUnit().equals(unit)) {
                    return price.getValue();
                }
            }
        }
        return null;
    }
}
