package org.example;

import java.util.Objects;

public abstract class Car {
    public String name;
    public int horsePower;
    public double price;
    public Car(){

    }
    public Car(String name, int horsePower, double price) {
        this.name = name;
        this.horsePower = horsePower;
        this.price = price;
    }
     public abstract void carMakes();
    public abstract void carMakes(int x,String name);
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return horsePower == car.horsePower && Double.compare(price, car.price) == 0 && Objects.equals(name, car.name);
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", horsePower=" + horsePower +
                ", price=" + price +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, horsePower, price);
    }
}
