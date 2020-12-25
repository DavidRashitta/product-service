package com.david.tasks.productservice;

import com.david.tasks.productservice.service.ProductService;
import com.david.tasks.productservice.util.FilesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;

@SpringBootApplication
public class ProductServiceApplication implements CommandLineRunner {

    @Value("${xml.file.path}")
    Resource productsXmlFile;

    @Value("${json.file.path}")
    Resource priceListJsonFile;

    private Logger logger = LoggerFactory.getLogger(ProductService.class);

    private FilesUtil filesUtil;

    @Autowired
    public ProductServiceApplication(FilesUtil filesUtil) {
        this.filesUtil = filesUtil;
    }

    @Override
    public void run(String... args) {
        logger.info("Reading XML and JSON files at application startup ....");
        filesUtil.mergePricesToProductsList(productsXmlFile, priceListJsonFile);
    }

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

}
