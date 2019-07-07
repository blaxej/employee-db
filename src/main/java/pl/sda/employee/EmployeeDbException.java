package pl.sda.employee;

public class EmployeeDbException extends RuntimeException {

    public EmployeeDbException(String message) {
        super(message);
    }

    public EmployeeDbException(String message, Throwable cause) {
        super(message, cause);
    }
}
