package pl.sda;

import pl.sda.employee.EmployeeDb;
import pl.sda.employee.InMemoryEmployeeDB;
import pl.sda.task.InMemoryTaskDB;
import pl.sda.task.TaskDB;

public class DbTestUtils {
    public static EmployeeDb emptyInMemoryEmployeeDb(){
        return new InMemoryEmployeeDB();
    }

    public static TaskDB emptyInMemmoryTaskDb() {
        return new InMemoryTaskDB();
    }
}
