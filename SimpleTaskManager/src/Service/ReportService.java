package Service;

import Model.Task;
import Model.TaskSummary;

import java.util.*;
import java.util.stream.Collectors;

public class ReportService {

    public void generateTaskSummary(Set<Task> set){
        int totalTasks = set.size();
        int completed = (int) set.stream().filter(Task::isComplete).count();
        TaskSummary summary = new TaskSummary(totalTasks,completed,(totalTasks-completed));
        System.out.println(summary);
    }
    public Optional<Task> findHighestPriority(Set<Task> set){
        return set.stream().max(Comparator.reverseOrder());
    }
    public Map<Boolean, List<Task>> groupByStatus(Set<Task> set){
        return set.stream().collect(Collectors.groupingBy(Task::isComplete));
    }
}
