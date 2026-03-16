package Service;

import Exceptions.DataExportException;
import Exceptions.ImportDataException;
import Interfacce.FileManager;
import Model.Task;

import java.io.*;
import java.util.Set;

public class IOService implements FileManager {

    @Override
    public TaskManager<Task> importData(File fileName) throws ClassNotFoundException {
        try {
            var fis = new FileInputStream((fileName));
            var ois = new ObjectInputStream(fis);
            return (TaskManager<Task>) ois.readObject();
        }catch (FileNotFoundException e){
            throw new ImportDataException(" Brak pliku");
        }catch (IOException e){
            throw new ImportDataException("Blad wczytywania pliku");
        }catch (ClassNotFoundException e){
            throw new ClassNotFoundException();
        }
    }

    @Override
    public void exportData(TaskManager<Task> manager,File fileName) {
        try {
            var fos = new FileOutputStream((fileName));
            var oos = new ObjectOutputStream(fos);
            oos.writeObject(manager);
        } catch (FileNotFoundException e) {
            throw new DataExportException("Brak pliku");
        } catch (IOException e) {
            throw new DataExportException("Blad zapisu danych do pliku");
        }

    }
}
