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
            if (employeeFromDb.getTask() == null) {
                task.ifPresent(taskFromDb -> {
                    if (taskFromDb.getEmployee() == null) {
                        employeeFromDb.setTask(taskFromDb);
                        taskFromDb.setEmployee(employeeFromDb);
                    } else
                        throw new TaskAlreadyAssignedException("Task already assigned to " + taskFromDb.getEmployee());
                });
            } else throw new EmployeeAlreadyBusyException("Employee is already busy" + employeeFromDb.getTask());
        });
    }

    public Optional<Task> getEmployeeTask(long employeeId) {
        return employeeDB.findById(employeeId).map(employee -> employee.getTask());
    }
}
