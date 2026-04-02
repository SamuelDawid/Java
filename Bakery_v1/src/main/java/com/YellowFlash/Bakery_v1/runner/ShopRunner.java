package com.YellowFlash.Bakery_v1.runner;

import com.YellowFlash.Bakery_v1.service.BakeryService;
import com.YellowFlash.Bakery_v1.service.OrderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Indexed;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

@Component
public class ShopRunner implements CommandLineRunner {

    private final OrderService orderService;
    Scanner scanner;
    private final String shopName;
    private final boolean currentMode;
    public ShopRunner(OrderService orderService,
                      @Value("${shop.dev.mode}") boolean mode,
                      @Value("${shop.name}") String shopName){
        this.orderService = orderService;
        this.scanner = new Scanner(System.in);
        this.currentMode = mode;
        this.shopName = shopName;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(shopName + " otwarty!");
        orderService.printMenu();
            String order;


            do {
                try{
                    System.out.println("Food choice : ");
                    String foodChoice = scanner.nextLine();
                    System.out.println("Kawa choice : ");
                    String coffeeChoice = scanner.nextLine();
                    order = orderService.placeOrder(foodChoice,coffeeChoice);
                    if(currentMode){
                        System.out.println("[DevMode] Zamowiono: " + foodChoice +" and "+ coffeeChoice);
                        System.out.println("[DevMode] Result: " + order);
                    }else {
                        System.out.println(order);
                    }
                    break;
                }catch (IllegalArgumentException e){
                    System.out.println(e.getMessage());
                    order = "";
                }
            }while (order.isEmpty());
        }





}
