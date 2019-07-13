package pl.sda;

import pl.sda.employee.EmployeeDb;
import pl.sda.employee.FileEmployeeDb;
import pl.sda.employee.InMemoryEmployeeDB;
import pl.sda.task.FileTaskDB;
import pl.sda.task.InMemoryTaskDB;
import pl.sda.task.Task;
import pl.sda.task.TaskDB;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

public class DbTestUtils {
    public static EmployeeDb emptyInMemoryEmployeeDb() {
        return new InMemoryEmployeeDB();
    }

    public static TaskDB emptyInMemmoryTaskDb() {
        return new InMemoryTaskDB();
    }

    public static TaskDB emptyFileTaskDB() throws IOException {
        return new FileTaskDB(Files.createTempFile
                ("task-db", ".txt"), Charset.forName("UTF-8"));
    }


    public static Task taskWithTitle(String title) {
        return new Task(title, "any description", "BUG", false);
    }

    public static EmployeeDb emptyFileEmployeeDb() throws IOException {
        return new FileEmployeeDb(Files.createTempFile("employee-db"
                , ".txt"), Charset.forName("UTF-8"));
    }
}

