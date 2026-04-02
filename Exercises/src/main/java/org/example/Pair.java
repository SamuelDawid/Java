package org.example;

public class Pair <A,B>{

    private final A value;
    private final B valueTwo;

    public Pair(A value,B valueTwo){
        this.value =value;
        this.valueTwo = valueTwo;
    }
    public void getFirst(){
        System.out.println(value);;
    }

    public void getSecond(){
        System.out.println(valueTwo);
    }

    public Pair<B,A> swap(){
        return new Pair<>(valueTwo,value);
    }
}
