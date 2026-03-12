package com.sklep;

import com.sklep.Enums.DeliveryOptions;
import com.sklep.Enums.ProductCategory;
import com.sklep.cart.Cart;
import com.sklep.catalog.ProductCatalog;
import com.sklep.model.DigitalProduct;
import com.sklep.model.PhysicalProduct;
import com.sklep.order.Order;
import com.sklep.order.OrderService;

import java.io.File;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ProductCatalog productCatalog = new ProductCatalog();
        Cart cart = new Cart();
        OrderService orderService = new OrderService();


        productCatalog.addProduct(ProductCategory.Clothing,
                new DigitalProduct("IntelliJ License", "IDE license", 4, 299.0, ProductCategory.Electronics, 50, new File("license.key")));
        productCatalog.addProduct(ProductCategory.Clothing,
                new PhysicalProduct("Coffee Mug", "Ceramic mug", 5, 39.0, ProductCategory.Kitchen, DeliveryOptions.DPD, 0.4));
        productCatalog.addProduct(ProductCategory.Clothing,
                new PhysicalProduct("Laptop", "Gaming laptop", 1, 3500.0, ProductCategory.Electronics, DeliveryOptions.DHL, 2.5));
        productCatalog.addProduct(ProductCategory.Clothing,
                new PhysicalProduct("T-Shirt", "Cotton t-shirt", 2, 79.0, ProductCategory.Clothing, DeliveryOptions.Mail, 0.3));


        cart.addProduct(new PhysicalProduct("Laptop", "Gaming laptop", 1, 3500.0, ProductCategory.Electronics, DeliveryOptions.DHL, 2.5));
        cart.addProduct(new PhysicalProduct("Laptop", "Gaming laptop", 1, 3500.0, ProductCategory.Electronics, DeliveryOptions.DHL, 2.5));
        cart.addProduct(new PhysicalProduct("Laptop", "Gaming laptop", 1, 3500.0, ProductCategory.Electronics, DeliveryOptions.DHL, 2.5));

        orderService.placeOrder(cart);

        List<Order> orderList = orderService.getOrderHistory();

        for (Order o: orderList)
            System.out.println(o);

        System.out.println(orderService.getTotalSpent());






















    }

}