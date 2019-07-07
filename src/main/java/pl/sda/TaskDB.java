package pl.sda;

import java.util.Optional;

public interface TaskDB {
    long add(Task task);

    Iterable<Task> findAll();

    Optional<Task> findById(long id);
}
