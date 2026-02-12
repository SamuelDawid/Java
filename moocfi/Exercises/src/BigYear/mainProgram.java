package BigYear;

import java.util.ArrayList;
import java.util.Scanner;

public class mainProgram {
    public static void main(String[] args) {
        ArrayList<Bird> allBirds = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("?");
            String input = scanner.nextLine();

            switch (input){
                case "Add":
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.println("Name in Latin: ");
                    String inLatin = scanner.nextLine();
                    Bird newBird = new Bird(name,inLatin);
                    if(!allBirds.contains(newBird))
                    allBirds.add(newBird);
                    break;
                case "Observation":
                    System.out.print("Bird?");
                    String birdToObserve = scanner.nextLine();
                    for (Bird bird : allBirds){
                        if(bird.getName().equals(birdToObserve))
                            bird.addOneObservation();
                    }
                    break;
                case "All":
                    for (Bird b : allBirds)
                        System.out.println(b.toString());
                    break;
                case "One":
                    System.out.print("Bird?");
                    String birdToPrint = scanner.nextLine();
                    for (Bird bird : allBirds){
                        if(bird.getName().equals(birdToPrint))
                            System.out.println(bird);
                    }
                    break;
                case "Quit":
                    return;

            }



        }
    }
}
