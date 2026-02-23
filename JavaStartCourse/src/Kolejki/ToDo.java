package Kolejki;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class ToDo  {

    static void main() {
        Queue<Task> toDoList = new PriorityQueue<>();
        Scanner scanner = new Scanner(System.in);
        toDoList.add(new Task("Wazne","Cos robie waznego", Task.Priority.values()[1]));
        toDoList.add(new Task("BardzoWazne","Cos robie BardzoWazne", Task.Priority.values()[0]));
        toDoList.add(new Task("nieWazne","Cos robie nie waznego", Task.Priority.values()[2]));
        while (true) {
            System.out.println("""
                    Main menu: \s
                    1. Add Task\s
                    2. Show next Task\s
                    3. Exit
                    """);
            String choice = scanner.nextLine();
            switch (choice) {
                // dadaj nowe zadanie
                case "1":
                    System.out.println("Dodaj zadanie");
                    String name = scanner.nextLine();
                    System.out.println("Dodaj desckrypcje");
                    String description = scanner.nextLine();
                    System.out.println("""
                            Dodaj piorytet:\s
                            1.High \s
                            2.Moderate \s
                            3.Low\s
                            Select(1-3):
                            """);
                    int prio = scanner.nextInt();
                    toDoList.offer(new Task(name, description, Task.Priority.values()[prio - 1]));
                    break;
                case "2":
                    //pobierz kolejne zadanie z kolejki o najwyzszym piorytecie
                    System.out.println(toDoList);
                    System.out.println(toDoList.poll());

                    break;
                case "3":
                    // wyjdz z aplikacji
                    return;
            }
        }
    }
}
