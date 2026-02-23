package Kolejki;

public class Task implements Comparable<Task>{
    String name,description;

    @Override
    public int compareTo(Task o) {
        return this.priority.compareTo(o.priority);
    }

    enum Priority {HIGH,MODERATE,LOW}
    Priority priority;
    public Task(String name, String description, Priority _priority) {
        this.name = name;
        this.description = description;
        this.priority = _priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                '}';
    }
}

