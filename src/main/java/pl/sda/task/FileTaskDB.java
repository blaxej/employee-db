package pl.sda.task;

import pl.sda.employee.EmployeeDbException;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;

public class FileTaskDB implements TaskDB {
    private final Path path;
    private final Charset charset;
    //private Collection<Task> tasks;



    public FileTaskDB(Path path, Charset charset) {
        this.path = path;
        this.charset = charset;
    }

    @Override
    public long add(Task task) {
        try {
            long id = Files.readAllLines(path).size()+1;
            Task taskCopy = new Task(task,id);
            Files.write(path, Arrays.asList(taskCopy.asLine()), charset, StandardOpenOption.APPEND);
            /*lapiemy wyjatek IOException i zwracamy nasz wyjatek z wiadomoscia
            co poszlo nie tak. Dodatkowo poprzez zapis "employee" wiemy ktorego
            pracownika nie udalo sie dodac, a "e" daje nam informacje o tym
            co wywolalo wyjatek.
             */
            return id;
        } catch (IOException e) {
            throw new TaskDbException(String.format("fail to add new employee %s",
                    task), e);
        }
    }

    @Override
    public Iterable<Task> findAll() {
        return findAllAsList();
    }

    @Override
    public Optional<Task> findById(long id) {
        return findAllAsList().stream().filter(task -> task.getId() == id)
                .findAny();
    }

    @Override
    public Optional<Task> findByDescription(String description) {
        return findAllAsList().stream().filter(task -> task.getDescription().equals(description))
                .findAny();
    }

    @Override
    public Optional<Task> findByType(String type) {
        return findAllAsList().stream().filter(task -> task.getType().equals(type))
                .findAny();
    }

    @Override
    public Optional<Task> findByPrioryty(boolean priority) {
        return findAllAsList().stream().filter(task -> task.isPriority() == priority)
                .findAny();
    }

    private List<Task> findAllAsList() {
        try {
            return Files.lines(path, charset).map(line -> {
                String[] taskData = line.split(",");
                Task task = new Task(Long.parseLong(taskData[0]),taskData[1]
                        ,taskData[2],taskData[3],Boolean.parseBoolean(taskData[4]));
                return task;
            }).collect(Collectors.toList());
        } catch (IOException e) {
            throw new EmployeeDbException("Can not find task", e);
        }
    }

//    private long generateId() {
//        return tasks.size() + 1;
//    }
}
