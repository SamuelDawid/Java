package org.example;

import com.sun.source.tree.Tree;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        List<String> stringList = List.of("anna", "bartek", "celina");
        Function<String, Integer> returnStringLength = String::length;
        List<Integer> newList = stringList.stream().map(returnStringLength).toList();
        System.out.println(stringList);
        System.out.println(newList);

        System.out.println(max(List.of(3,1,7,2,5)));
        System.out.println(max(List.of("banana","apple","cherry")));
    }
    public static <T extends Comparable<T>> T max(List<T> list){
        T maxElement = list.getFirst();
        for (T item : list) {
            if(item.compareTo(maxElement) > 0)
                maxElement = item;
        }
        return maxElement;
    }
        public static <T> void swap(List<T> list,int i,int j){
            T temp = list.get(i);
            list.set(i,list.get(j));
            list.set(j,temp);
        }
    }
      class Zadanie implements Comparable<Zadanie>{
        String nazwa;
        int priorytet;

         public Zadanie(String nazwa, int priorytet) {
             this.nazwa = nazwa;
             this.priorytet = priorytet;
         }

         public String getNazwa() {
            return nazwa;
        }

        public int getPriorytet() {
            return priorytet;
        }
         public String toString() { return nazwa + "(" + priorytet + ")"; }
        @Override
        public int compareTo(Zadanie o) {
            return Integer.compare(this.priorytet,o.priorytet)
                    ;
        }
    }



