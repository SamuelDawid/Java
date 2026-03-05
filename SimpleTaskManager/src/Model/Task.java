package Model;

import Enuums.Priority;
import Interfacce.Printable;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Task implements Printable {
    String title;
    Priority priority;
    LocalDate dueDate;

    @Override
    public void printDetails() {
        System.out.println(this.toString());
    }

    public Task(String title, Priority priority, LocalDate dueDate) {
        this.title = title;
        this.priority = priority;
        this.dueDate = dueDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(title, task.title) && priority == task.priority && Objects.equals(dueDate, task.dueDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, priority, dueDate);
    }

    @Override
    public String toString() {
        return "Model.Task{" +
                "title='" + title + '\'' +
                ", priority=" + priority +
                ", dueDate=" + dueDate +
                '}';
    }
}

