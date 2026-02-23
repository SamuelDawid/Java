package Lambda;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class ExerciseOne {

    static void main() {
        Supplier<ArrayList<Integer>> tenRandomInt = () -> {
            Random rnd = new Random();
            ArrayList<Integer> listToReturn = new ArrayList<>();

            for(int i = 0; i < 10;i++){
                listToReturn.add(rnd.nextInt(1000));
            }

            return  listToReturn;
        };

        Consumer<List<Integer>> prinList = (l) ->{
            for (int i : l){
                System.out.print(i + " ");
            }
            System.out.println();
        };
        prinList.accept(tenRandomInt.get());
        System.out.println("3: ");
        //Następnie napisz metodę, która usunie z listy wszystkie liczby podzielne przez 2 (użyj iteratora i własnej metody wykorzystującej interfejs Predicate).
        prinList.accept(removeModulo(tenRandomInt.get()));

    }

    static List<Integer> removeModulo(ArrayList<Integer> list){
        Predicate<Integer> checkIfDivByTwo = num -> num % 2 == 0;
        Iterator<Integer> iterator = list.iterator();
        List<Integer> listToReturn = new ArrayList<>();

        while (iterator.hasNext()){
            int num = iterator.next();
            if(checkIfDivByTwo.test(num))
                iterator.remove();
            else
                listToReturn.add(num);
        }
        return listToReturn;
    }
    private static <T> void generate(List<T> list,int upperLimit, Supplier<T> supplier){
        for(int i = 0; i < upperLimit;i++){
            list.add(supplier.get());
        }

    }



}
