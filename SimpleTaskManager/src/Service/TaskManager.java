package Service;

import Enuums.Priority;
import Exceptions.TaskNotFoundException;
import Model.PersonalTask;
import Model.Task;
import Model.WorkTask;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TaskManager<T extends Task> implements Serializable {
    Set<T> listOfTasks;

    public TaskManager() {
        this.listOfTasks = new TreeSet<>();
    }
    public void findByTitleCheck(String title) throws TaskNotFoundException{
        Optional<T> taskToFind =  findByTitle(title);
        if(taskToFind.isPresent()) {
            System.out.println(taskToFind.get());
        }else{
            throw new TaskNotFoundException("Task not found");
        }
    }
    public Optional<T> findByTitle(String title) {
        return listOfTasks.stream().filter(t -> t.getTitle().equals(title)).findAny();
    }
    public Set<Task> getFilteredTask(Predicate<Task> predicate) {
        return listOfTasks.stream().filter(predicate).collect(Collectors.toCollection(TreeSet<Task>::new));
    }

    public boolean addTask(T newTask) {
       if(newTask == null) return false;
       return listOfTasks.add(newTask);
    }
    public boolean removeTask(String title){
        Optional<T> taskToRemove = findByTitle(title);
        if(taskToRemove.isPresent()){
            listOfTasks.remove(taskToRemove.get());
            return true;
        }
        return false;
    }
    public Task createTask() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    1.Work Task\s
                    2.Personal Task\s
                    Chose type:\s
                    """);

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
                    return new WorkTask(_title, Priority.valueOf(priorType), LocalDate.parse(dueDate), _projectName);

                case "2":
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
                    return new PersonalTask(_titlePersonal, Priority.valueOf(priorTypePersonal), LocalDate.parse(dueDatePersonal), _category);
                default:
                    System.out.println("Wrong input");
            }
        }

    }

}
