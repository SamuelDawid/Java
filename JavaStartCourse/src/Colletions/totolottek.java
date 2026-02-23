package Colletions;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class totolottek {

    static void main() {
        Lotto newLotto = new Lotto();
        Scanner scanner = new Scanner(System.in);
        List<Integer> customerList = new ArrayList<>();
        //
        System.out.println("Podaj 6 liczb 1 po drugiej wcisnij enter(od 1 do 49)");
        for (int i = 0; i < 6;i++){
            customerList.add(scanner.nextInt());
            scanner.nextLine();
        }

        int wins = newLotto.checkResult(customerList);
        System.out.println(wins);
    }


}
