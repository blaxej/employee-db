package pl.sda.task;

import java.util.Optional;

public interface TaskDB {
    long add(Task task);

    Iterable<Task> findAll();

    Optional<Task> findById(long id);

    Optional<Task> findByDescription (String description);

    Optional<Task> findByType (String type);

    Optional<Task> findByPrioryty (boolean prioryty);

}
