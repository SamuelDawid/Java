package NumbersFromAFile;


import java.nio.file.Paths;
import java.util.Scanner;

public class NumbersFromAFile {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("File? ");
        String file = scanner.nextLine();
        System.out.print("Lower bound? ");
        int lowerBound = Integer.valueOf(scanner.nextLine());
        System.out.print("Upper bound? ");
        int upperBound = Integer.valueOf(scanner.nextLine());
        int numbers = 0;

        try(Scanner scr = new Scanner(Paths.get(file))){
            while(scr.hasNextLine()){
                int boundCheck =Integer.valueOf(scr.nextLine());
                if(boundCheck >= lowerBound && boundCheck <= upperBound){
                    numbers++;
                }
            }
            System.out.println("Numbers: "+numbers);
        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

}

