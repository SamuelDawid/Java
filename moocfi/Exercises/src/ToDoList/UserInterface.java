package ToDoList;

import java.util.Scanner;

public class UserInterface {
    private TodoList list;
    Scanner scanner;

    public UserInterface(TodoList list, Scanner scanner) {
        this.list = list;
        this.scanner = scanner;
    }

    public void start(){

        while(true){
            System.out.print("Command:");
            String input = scanner.nextLine();
            switch(input){
                case "stop":
                    System.out.print("Bye bye!");
                    return;
                case "add":
                    System.out.print("To add:");
                    String toAdd = scanner.nextLine();
                    list.add(toAdd);
                    break;
                case "list":
                    list.print();
                    break;
                case "remove":
                    System.out.println("Which one is removed?");
                    int numberToRemove = scanner.nextInt();
                    scanner.nextLine();
                    list.remove(numberToRemove);
                    break;
                default:
                    System.out.println("Unknown command");
                    break;

            }
        }
    }
}
