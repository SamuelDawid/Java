import Exceptions.TaskNotFoundException;
import Model.Task;
import Service.IOService;
import Service.ReportService;
import Service.TaskManager;
import UI.MenuManager;

import java.io.File;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;

public class App {

    public static void main(String[] args) {
        TaskManager<Task> taskManager = new TaskManager<>();
        MenuManager ui = new MenuManager();
        ReportService reportService = new ReportService();
        IOService ioService = new IOService();
        Scanner scanner = new Scanner(System.in);
        do {
            ui.displayMainMenu();
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    System.out.println("Load from file?");
                    String answerInput = scanner.nextLine();

                    if (answerInput.equalsIgnoreCase("yes")) {
                        System.out.println("file name: ");
                        String fileName = scanner.nextLine();
                        File file = new File(fileName);

                        boolean fileExist = file.exists();
                        if (fileExist) {
                            try {
                                TaskManager<Task> imported = ioService.importData(file);
                                imported.getFilteredTask(task -> true).forEach(taskManager::addTask);
                                System.out.println("loaded");
                                break;
                            } catch (ClassNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                    if (taskManager.addTask(taskManager.createTask())) {
                        System.out.println("Task added");
                        break;
                    }
                    System.out.println("Invalid task");
                    break;
                case "2":
                    Set<Task> taskSet = taskManager.getFilteredTask(task -> true);
                    for (Task t : taskSet)
                        System.out.println(t);
                    break;
                case "3":
                    System.out.println("Provide title of the task: ");
                    String title = scanner.nextLine();
                    try {
                        taskManager.findByTitleCheck(title);
                    } catch (TaskNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "4":
                    System.out.println("Provide task title");
                    String titleInput = scanner.nextLine();
                    if (taskManager.removeTask(titleInput))
                        System.out.println("Task has been deleted");
                    break;
                case "5":
                    reportService.generateTaskSummary(taskManager.getFilteredTask(task -> true));
                    break;
                case "6":
                    System.out.println("file name: ");
                    String fileName = scanner.nextLine();
                    File file = new File(fileName);
                    ioService.exportData(taskManager, file);
                    System.out.println("Saved!");
                    return;
                default:
                    System.out.println("Invalid input");
            }
        } while (true);

    }


}
