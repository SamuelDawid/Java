package org.example;

import java.util.List;

public class Subaru extends Car{

    public Subaru(String name, int horsePower, double price) {
        super(name, horsePower, price);

    }

    public Subaru(){
        this.name = "Subaru";
        this.horsePower = 200;
        this.price = 4500.65;
    }

    @Override
    public void carMakes() {
        System.out.println("Wruum");
    }

    @Override
    public void carMakes(int x, String name) {
        for (int i = 0; i < x; i++) {
            System.out.println(name);
        }
    }
}
