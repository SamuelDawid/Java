package com.sklep.order;

import com.sklep.model.Product;

import java.time.LocalDateTime;
import java.util.List;

public record Order(
        int orderID,
        List<Product> productsList,
        LocalDateTime dateTime,
        double total
                    ) {
}
