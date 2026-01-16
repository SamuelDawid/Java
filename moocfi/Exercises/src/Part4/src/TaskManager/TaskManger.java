package TaskManager;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskManger {
    static class Task{
    private String description;
    private boolean isComplete;

    public Task(String _description){
        this.description = _description;
        this.isComplete = false;
    }
    public void markComplete(){
        this.isComplete = true;
    }
    public String getDescription(){
        return description;
    }
    public boolean isComplete(){
        return isComplete;
    }
    @Override
        public String toString(){
        String status = isComplete ? "[✓]" : "[ ]";
        return status + " " + description;
    }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Task Manger");
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running){
            System.out.println("\n=== MENU ===");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Complete Task");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    addTask(tasks, scanner);
                    break;
                case 2:
                    viewTask(tasks);
                    break;
                case 3:
                    completeTask(tasks, scanner);
                    break;
                case 4:
                    deleteTask(tasks, scanner);
                    break;
                case 5:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }

        }
        scanner.close();
    }
    private static void addTask(ArrayList<Task> tasks, Scanner scanner){
        System.out.println("Enter task description: ");
        String description = scanner.nextLine();
        tasks.add(new Task(description));
        System.out.println("Task added successfully!");
    }
    private static void viewTask(ArrayList<Task> tasks){
        if(tasks.isEmpty()){
            System.out.println("No tasks yet. Add some!");
            return;
        }
        System.out.println("\n=== YOUR TASKS ===");
        for(Task task : tasks){
            System.out.println(task.toString());
        }
    }
    private static void completeTask(ArrayList<Task> tasks, Scanner scanner){
        viewTask(tasks);
        if(tasks.isEmpty()) return;

        System.out.println("Enter task number to complete: ");
        int taskNum = scanner.nextInt();
        scanner.nextLine();

        if(taskNum > 0 && taskNum <= tasks.size()){
            tasks.get(taskNum - 1).markComplete();
            System.out.println("Task marked as complete!");
        } else {
            System.out.println("Invalid task number.");
        }

    }
    private static void deleteTask(ArrayList<Task> tasks, Scanner scanner){
        viewTask(tasks);
        if (tasks.isEmpty()) return;

        System.out.print("Enter task number to delete: ");
        int taskNum = scanner.nextInt();
        scanner.nextLine();

        if (taskNum > 0 && taskNum <= tasks.size()) {
            tasks.remove(taskNum - 1);
            System.out.println("Task deleted!");
        } else {
            System.out.println("Invalid task number.");
        }
    }
}
