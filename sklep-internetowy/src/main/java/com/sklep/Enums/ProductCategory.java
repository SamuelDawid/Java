package com.sklep.Enums;

public enum ProductCategory {
    Clothing("Clothing"),
    Electronics("Electronics"),
    Home("Home"),
    Kitchen("Kitchen"),
    Health("Health");

    private final String displayName;

    ProductCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
