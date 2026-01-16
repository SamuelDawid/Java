package LiquidContainers;

import java.util.Scanner;

public class LiquidContainers {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Container first =new Container() , second = new Container();


        while (true) {
            System.out.println("First: " + first.contains()+"/"+first.maxAmount);
            System.out.println("Second: " + second.contains()+"/"+second.maxAmount);

            String input = scan.nextLine();
            if (input.equals("quit")) {
                break;
            }
            String[] parts = input.split(" ");
            String command = parts[0];
            switch(command){
                case "add":
                        first.add(Integer.parseInt(parts[1]));
                        break;

                case "move":

                    if( Integer.parseInt(parts[1]) < 0){
                        break;
                    }else
                    {
                        if(first.contains() > 0 ){
                            if(first.contains() > Integer.parseInt(parts[1])){
                                second.add(Integer.parseInt(parts[1]));
                                first.remove(Integer.parseInt(parts[1]));
                                break;
                            }
                            if(first.contains() <= Integer.parseInt(parts[1])){
                                second.add(first.contains());
                                first.remove(first.contains());
                            }

                                break;
                            }
                    }
                case "remove":
                    if(second.contains() <= 0 || Integer.parseInt(parts[1]) < 0)
                    break;
                    else {
                        second.remove(Integer.parseInt(parts[1]));
                        break;
                    }
                case "quit":
                    return;

            }
        }
    }
}
