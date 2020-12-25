package com.david.tasks.productservice.modal;

import java.util.List;

public class Product {
    private String id;
    private String name;
    private String description;
    private String SKU;
    private List<Price> priceList;

    public Product() {
    }

    public Product(String id, String name, String description, String SKU) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.SKU = SKU;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public List<Price> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<Price> priceList) {
        this.priceList = priceList;
    }
}
