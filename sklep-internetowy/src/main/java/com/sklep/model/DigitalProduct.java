package com.sklep.model;

import com.sklep.Enums.ProductCategory;

import java.io.File;

public class DigitalProduct extends Product{

    private final int copiesLeft;
    private final File file;

    public DigitalProduct(String name, String description, int id, double price, ProductCategory category, int copiesLeft, File file) {
        super(name, description, id, price, category);
        this.copiesLeft = copiesLeft;
        this.file = file;
    }

    public int getCopiesLeft() {
        return copiesLeft;
    }

    public File getFile() {
        return file;
    }
}
