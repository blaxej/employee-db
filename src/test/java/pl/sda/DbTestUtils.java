package pl.sda;

public class DbTestUtils {
    public static EmployeeDb emptyInMemoryEmployeeDb(){
        return new InMemoryEmployeeDB();
    }

    public static TaskDB emptyInMemmoryTaskDb() {
        return new InMemoryTaskDB();
    }
}
