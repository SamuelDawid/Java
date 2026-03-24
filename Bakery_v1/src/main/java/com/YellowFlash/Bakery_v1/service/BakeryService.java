package com.YellowFlash.Bakery_v1.service;

import org.springframework.stereotype.Service;

@Service
public class BakeryService {

    public String prepareOrder(String item){
        return "preparing " + item;
    }
}
