package Model;

import Enuums.Priority;

import java.time.LocalDate;

public class WorkTask extends Task{
     String projectName;

    public WorkTask(String title, Priority priority, LocalDate dueDate, String projectName) {
        super(title, priority, dueDate);
        this.projectName = projectName;
    }
}
