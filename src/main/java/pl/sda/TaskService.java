package pl.sda;

import java.util.Optional;

public class TaskService {

    private final EmployeeDB employeeDB;
    private final TaskDB taskDB;

    public TaskService(EmployeeDB employeeDB, TaskDB taskDB) {
        this.employeeDB = employeeDB;
        this.taskDB = taskDB;
    }

    public void assignTaskToEmployee(long taskId, long employeeId) {
        Optional<Employee> employee = employeeDB.findById(employeeId);
        Optional<Task> task = taskDB.findById(taskId);
        employee.ifPresent(employeeFromDb -> {
            task.ifPresent(taskFromDb -> {
                employeeFromDb.setTask(taskFromDb);
            });
        });
    }

    public Optional<Task> getEmployeeTask(long employeeId) {
        return employeeDB.findById(employeeId).map(employee -> employee.getTask());
    }
}
