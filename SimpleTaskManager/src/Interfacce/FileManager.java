package Interfacce;

import Model.Task;
import Service.TaskManager;

import java.io.File;

public interface FileManager {
    TaskManager<Task> importData(File fileName) throws ClassNotFoundException;
    void exportData(TaskManager<Task> manager,File fileName);
}
