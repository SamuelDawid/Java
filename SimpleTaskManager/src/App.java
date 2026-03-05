import Model.Task;
import Service.TaskManager;
import UI.MenuManager;

import javax.xml.transform.Source;
import java.util.Scanner;

public class App {

     static void main() {
         TaskManager<Task> taskManager = new TaskManager<>();
         MenuManager ui = new MenuManager();
         Scanner scanner = new Scanner(System.in);
         boolean exit = false;
         do {
             ui.displayMainMenu();
             String option = scanner.nextLine();

             switch (option){
                 case "1":
                     if(taskManager.addTask(taskManager.createTask())) {
                         System.out.println("Task added");
                         break;
                     }
                     System.out.println("Invalid task");
                     break;
                 case "2":

                     break;
                 case "3":
                     break;
                 case "4":
                     break;
                 case "5":
                     break;
                 case "6":
                     return;
             }
         }while (!exit);

    }


}
