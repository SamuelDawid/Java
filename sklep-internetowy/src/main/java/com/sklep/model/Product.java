package com.sklep.cart;

import com.sklep.Enums.ProductCategory;

import java.util.Locale;
import java.util.Objects;

public abstract class Product implements Comparable{
    private final String name,description;
    private final int price,id;
    private final ProductCategory category;

    public Product(String name, String description,int id, int price, ProductCategory category) {
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
        return price == product.price && id == product.id && Objects.equals(name, product.name) && Objects.equals(description, product.description) && category == product.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, price, id, category);
    }
}
