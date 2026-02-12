package OneTimePrograms;

import java.util.ArrayList;
import java.util.Scanner;

public class LeapYear {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LeapYear year = new LeapYear();
        ArrayList<Integer> list = new ArrayList<>();

        // after that, the program prints the smallest number
        // and its index -- the smallest number
        // might appear multiple times

        while (true) {
            // implement here a program that reads user input
            int smallest;
            int input = Integer.valueOf(scanner.nextLine());
            if (input == 9999) {
                smallest = year.Smallest(list);
                System.out.println("Smallest number: " + smallest);
                for(int i = 0; i <list.size();i++) {
                    if (list.get(i) == smallest) {
                        System.out.println("Found at index: " + i);
                    }
                }
                break;
            }

            list.add(input);
        }
    }
    public int Smallest(ArrayList<Integer> list) {
        int smallest = list.getFirst();
        for (int number : list) {
            if (smallest > number) {
                smallest = number;
            }
        }
        return smallest;
    }
    public static void removeLast(ArrayList<String> strings){
        strings.remove(strings.getLast());

    }

}

