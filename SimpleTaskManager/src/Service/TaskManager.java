package Service;

import Enuums.Priority;
import Model.PersonalTask;
import Model.Task;
import Model.WorkTask;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

public class TaskManager<T extends Task> {
    Set<T> listOfTasks;

    public TaskManager() {
        this.listOfTasks = new HashSet<>();

    }
    public Set<Task>getFilteredTask(Predicate<T> predicate){
        Set<Task> filteredTasks = new HashSet<>();

    }
    public boolean addTask(Task newTask) {
        Optional<Task> taskToAdd = Optional.ofNullable(newTask);

        if(taskToAdd.isPresent()) {
            listOfTasks.add((T) taskToAdd.get());
            return true;
        }

        return false;
    }

    public Task createTask() {
        while (true) {
            System.out.println("""
                    1.Work Task\s
                    2.Personal Task\s
                    Chose type:\s
                    """);
            Scanner scanner = new Scanner(System.in);
            String Option = scanner.nextLine();
            switch (Option) {
                case "1":
                    // String title, Priority priority, Date dueDate, String projectName

                    System.out.println("projectName: ");
                    String _projectName = scanner.nextLine();
                    System.out.println("Title: ");
                    String _title = scanner.nextLine();
                    System.out.println("""
                            Priority:
                            High, \s
                            Medium, \s
                            Low\s
                            Chose type:
                            """);
                    String priorType = scanner.nextLine();
                    System.out.println("Date (YYYY-MM-DD): ");
                    String dueDate = scanner.nextLine();

                    return new  WorkTask(_title, Priority.valueOf(priorType), LocalDate.parse(dueDate),_projectName);

                case "2":
                    // String title, Priority priority, Date dueDate, String projectName

                    System.out.println("category: ");
                    String _category = scanner.nextLine();
                    System.out.println("Title: ");
                    String _titlePersonal = scanner.nextLine();
                    System.out.println("""
                            Priority:
                            High, \s
                            Medium, \s
                            Low\s
                            Chose type:
                            """);
                    String priorTypePersonal = scanner.nextLine();
                    System.out.println("Date (YYYY-MM-DD): ");
                    String dueDatePersonal = scanner.nextLine();

                    return new PersonalTask(_titlePersonal,Priority.valueOf(priorTypePersonal), LocalDate.parse(dueDatePersonal),_category);
            }
        }
    }

}
