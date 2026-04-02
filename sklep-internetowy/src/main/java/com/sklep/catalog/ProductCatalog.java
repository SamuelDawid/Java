package com.sklep.catalog;

import com.sklep.Enums.ProductCategory;
import com.sklep.model.Product;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProductCatalog {
    private HashMap<ProductCategory,List<Product>> catalog = new HashMap<>();

    public boolean addProduct(ProductCategory c, Product p) throws ProductNotFoundException {
        if(c != null && p != null) {
            catalog.computeIfAbsent(c, k -> new ArrayList<>()).add(p);
        return true;
        }
        throw new ProductNotFoundException("Product or category not found");
    }

    public Optional<List<Product>> getProductsByCategory(ProductCategory c){
        return Optional.ofNullable(catalog.get(c));
    }
    public List<Product> searchByName(String name) {
        return catalog.values().stream().flatMap(Collection::stream).filter(product -> product.getName().equals(name)).collect(Collectors.toList());
    }
    public List<Product> getAllProducts(){
        return  catalog.values().stream().flatMap(Collection::stream).collect(Collectors.toList());
    }

}
