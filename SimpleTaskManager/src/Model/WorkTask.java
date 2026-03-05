package Model;

import Enuums.Priority;

import java.time.LocalDate;
import java.util.Date;

public class WorkTask extends Task{
    private String projectName;

    public WorkTask(String title, Priority priority, LocalDate dueDate, String projectName) {
        super(title, priority, dueDate);
        this.projectName = projectName;
    }
}
