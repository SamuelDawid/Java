package com.sklep.model;

import com.sklep.Enums.ProductCategory;

import java.util.Objects;

public abstract class Product {
    private final String name,description;
    private final int id;
    private final double price;
    private final ProductCategory category;

    public Product(String name, String description,int id, double price, ProductCategory category) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.price = price;
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public ProductCategory getCategory() {
        return category;
    }
}
