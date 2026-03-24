package com.YellowFlash.Bakery_v1.runner;

import com.YellowFlash.Bakery_v1.service.OrderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ShopRunner implements CommandLineRunner {

    private final OrderService orderService;
    Scanner scanner;
    @Value("${shop.name}")
    String shopName;
    @Value("${shop.dev.mode}")
    boolean devMode;

    public ShopRunner(OrderService orderService){
        this.orderService = orderService;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(shopName + " otwarty!");

        System.out.println("Co podac?");
        String foodChoice = "Croissant";
        String coffeeChoice = "Latte";
        orderService.placeOrder(foodChoice,coffeeChoice);
    }
}
