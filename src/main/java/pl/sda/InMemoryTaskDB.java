package pl.sda;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class InMemoryTaskDB implements TaskDB {

    private Collection<Task> tasks;

    public InMemoryTaskDB() {
        tasks = new ArrayList<>();
    }

    @Override
    public long add(Task task) {
        long id = generateId();
        Task taskCopy = new Task(task, id);
        tasks.add(taskCopy);
        return id;
    }

    @Override
    public Iterable<Task> findAll() {
        return tasks;
    }

    @Override
    public Optional<Task> findById(long id) {
        return tasks.stream().filter(task -> task.getId() == id)
                .findAny();
    }

    private long generateId() {
        return tasks.size() + 1;
    }
}
