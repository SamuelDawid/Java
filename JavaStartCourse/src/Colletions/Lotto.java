package Colletions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Lotto {
    List<Integer> numberList ;

    public Lotto(){
        this.numberList = new ArrayList<>();
        generate();
        randomize();
    }
    void generate(){
        for (int i = 1; i < 50; i++) {
            numberList.add(i);
        }
    }
    void randomize(){
        Collections.shuffle(numberList);
    }
    int checkResult(List<Integer> numbers){
        List<Integer> selectedNumbers = numberList.subList(0,6);

        int counter = 0;
        for (int i : selectedNumbers){
            if(numbers.contains(i))
                counter++;
        }

        return counter;
    }

    @Override
    public String toString() {
        return "Lotto{" +
                "numberList=" + numberList +
                '}';
    }
}
