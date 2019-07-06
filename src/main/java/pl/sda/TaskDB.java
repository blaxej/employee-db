package pl.sda;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class TaskDB {

	private Collection<Task> tasks;

	public TaskDB() {
		tasks = new ArrayList<>();
	}

	public long add(Task task) {
		long id = generateId();
		Task taskCopy = new Task(task, id);
		tasks.add(taskCopy);
		return id;
	}

	public Iterable<Task> findAll() {
		return tasks;
	}

	public Optional<Task> findById(long id) {
		return tasks.stream().filter(task -> task.getId() == id)
			.findAny();
	}

	private long generateId() {
		return tasks.size() + 1;
	}
}
