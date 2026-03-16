package Model;

import Enuums.Priority;
import Interfacce.Printable;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public abstract class Task implements Serializable,Printable,Comparable<Task> {
    String title;
    Priority priority;
    LocalDate dueDate;
    boolean isComplete;

    @Override
    public void printDetails() {
        System.out.println(this);
    }

    public Task(String title, Priority priority, LocalDate dueDate) {
        this.title = title;
        this.priority = priority;
        this.dueDate = dueDate;
        this.isComplete = false;
    }

    @Override
    public int compareTo(Task o) {
        return this.priority.compareTo(o.priority);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return isComplete == task.isComplete && Objects.equals(title, task.title) && priority == task.priority && Objects.equals(dueDate, task.dueDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, priority, dueDate, isComplete);
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", priority=" + priority +
                ", dueDate=" + dueDate +
                ", isComplete=" + isComplete +
                '}';
    }
}

