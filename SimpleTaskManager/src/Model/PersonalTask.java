package Model;


import Enuums.Priority;

import java.time.LocalDate;

public class PersonalTask extends Task{
    String category;

    public PersonalTask(String title, Priority priority, LocalDate dueDate, String category) {
        super(title, priority, dueDate);
        this.category = category;
    }

}
