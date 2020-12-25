package com.david.tasks.productservice.modal;

public class Price {
    private Double value;
    private String currency;
    private String unit;

    public Price() {
    }

    public Price(Double value, String currency, String unit) {
        this.value = value;
        this.currency = currency;
        this.unit = unit;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public boolean equals(Object obj) {
        Price price = (Price) obj;

        return (price.getUnit().equals(this.getUnit()) && price.getValue().equals(this.getValue()) && price.getCurrency().equals(this.getCurrency()));
    }
}
