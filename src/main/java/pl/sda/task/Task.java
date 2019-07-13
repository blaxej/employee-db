package pl.sda.task;

import pl.sda.employee.Employee;

import java.util.Objects;

public class Task {
    private long id;
    private String title;
    private Employee employee;
    private String description;
    private String type;
    private boolean priority;

    public Task() {
    }

    public Task(Task task, long id) {
        this.id = id;
        title = task.title;
        description = task.description;
        type = task.type;
        priority = task.priority;

    }


    public Task(long id, String title, String description, String type, boolean priority ) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.title = title;
        this.id  = id;
this.priority = priority;
    }

    public Task(String title, String description, String type, boolean priority) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public boolean isPriority() {
        return priority;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPriority(boolean priority) {
        this.priority = priority;
    }

    public String asLine() {
        return id + "," + title + ","+ description +","+ type +","
                + priority + "," + extractEmployeeId();
    }
    private Long extractEmployeeId() {
        if(employee != null)
            return employee.getId();
        return null;
    }
}
