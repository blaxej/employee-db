package pl.sda;

public class TaskAlreadyAssignedException extends RuntimeException {
    public TaskAlreadyAssignedException(String message) {
        super(message);
    }

    public TaskAlreadyAssignedException(String message, Throwable cause) {
        super(message, cause);
    }
}
