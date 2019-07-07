package pl.sda;

import java.util.Optional;

public class TaskService {

    private final EmployeeDb employeeDB;
    private final TaskDB taskDB;

    public TaskService(EmployeeDb employeeDB, TaskDB taskDB) {
        this.employeeDB = employeeDB;
        this.taskDB = taskDB;
    }

    /**
     * Assigns a new task to the employee.
     *
     * @param taskId     task id
     * @param employeeId employee id
     * @throws TaskAlreadyAssignedException if task is already assigned to some other user
     * @throws EmployeeAlreadyBusyException when target employee is already working on another task
     */
    public void assignTaskToEmployee(long taskId, long employeeId) throws TaskAlreadyAssignedException, EmployeeAlreadyBusyException {
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
