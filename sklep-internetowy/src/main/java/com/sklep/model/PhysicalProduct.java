package com.sklep.model;

import com.sklep.Enums.DeliveryOptions;
import com.sklep.Enums.ProductCategory;

public class PhysicalProduct extends Product {
    private final DeliveryOptions deliveryOptions;
    private final double weight;

    public PhysicalProduct(String name, String description, int id, double price, ProductCategory category, DeliveryOptions deliveryOptions, double weight) {
        super(name, description, id, price, category);
        this.deliveryOptions = deliveryOptions;
        this.weight = weight;
    }

    public DeliveryOptions getDeliveryOptions() {
        return deliveryOptions;
    }

    public double getWeight() {
        return weight;
    }
}
