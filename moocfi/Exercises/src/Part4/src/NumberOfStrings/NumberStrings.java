package NumberOfStrings;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.ArrayList;

public class NumberStrings {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> listOfStrings = new ArrayList<>();

        while(true){
            String line = scanner.nextLine();
            listOfStrings.add(line);

            if(line.equals("end")){
                break;
            }

        }
        System.out.println(listOfStrings.size() -1);
    }
}
