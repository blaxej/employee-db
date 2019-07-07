package pl.sda.employee;

import pl.sda.task.Task;

import java.util.Objects;

public class Employee {
    private long id;
    private String name;
    private Task task;

    //tworzymy metode determinujaca format danych
    //dla kazdej z lini tworzonego pliku tekstowego

    public String asLine() {
        return id + "," + name + "," + extractTaskId();
    }

    private Long extractTaskId() {
        if(task != null)
            return task.getId();
        return null;
    }

    public Employee(String name) {


        this.name = name;
    }

    public Employee(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public long getId() {
        return id;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Task getTask() {
        return task;
    }
}
