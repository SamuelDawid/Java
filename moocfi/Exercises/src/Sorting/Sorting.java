package Sorting;

import java.util.Arrays;

public class Sorting {
    public static void main(String[] args) {
        // write your test code here
        int[] numbers = {8, 3, 7, 9, 1, 2, 4};
        Sorting.sort(numbers);
    }
    public static int smallest(int[] array){
        // write your code here
        int smallest = array[0];

        for(int i : array){
            if(i < smallest)
                smallest = i;
        }
        return smallest;
    }
    public static int indexOfSmallest(int[] array){
        // write your code here
        int smallestIndexNumber = Sorting.smallest(array);
        int index = 0;
        for(int i =0; i < array.length;i++){
            if(smallestIndexNumber == array[i])
                index = i;
        }
        return index;
    }
    public static int indexOfSmallestFrom(int[] array, int startIndex) {
        // write your code
        int indexOfSmallest = startIndex;
        for(int i = startIndex + 1; i < array.length;i++){
            if(array[i] < array[indexOfSmallest]){
                indexOfSmallest = i;
            }
        }
        return indexOfSmallest;
    }
    public static void swap(int[] array, int index1, int index2) {
        // write your code here
        int value = array[index1];
        int valueTwo = array[index2];

        array[index1] = valueTwo;
        array[index2] = value;
    }
    public static void sort(int[] array) {

    for(int i = 0; i < array.length;i++){


        System.out.println(Arrays.toString(array));
        Sorting.swap(array,i, Sorting.indexOfSmallestFrom(array,i));
    }
        System.out.println(Arrays.toString(array));
    }



}
