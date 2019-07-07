package pl.sda;

public class EmployeeAlreadyBusyException extends RuntimeException {
    public EmployeeAlreadyBusyException(String message) {
        super(message);
    }

    public EmployeeAlreadyBusyException(String message, Throwable cause) {
        super(message, cause);
    }
}
