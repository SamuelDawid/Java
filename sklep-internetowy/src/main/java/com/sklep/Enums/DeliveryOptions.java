package com.sklep.Enums;

public enum DeliveryOptions {
    DHL("Delivey 3-5 days",15),
    DPD("Delivey 1 days",25),
    Mail("Delivery 5-10 days",10);

    private final String description;
    private final int price;

     DeliveryOptions(String _description,int _price){
        this.description = _description;
        this.price = _price;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }
}
