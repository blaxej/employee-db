package pl.sda.task;

import java.io.IOException;

public class TaskDbException extends RuntimeException {
    public TaskDbException(String message) {
        super(message);
    }

    public TaskDbException(String message, Throwable cause) {
        super(message, cause);
    }
}
