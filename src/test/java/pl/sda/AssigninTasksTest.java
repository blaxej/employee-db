package pl.sda;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class AssigninTasksTest {

    @DisplayName("given employee without task" +
            "when assigne task to employee" +
            "then employee is working on this task")
    @Test
    void addTaskToEmployee() {
        //given
        EmployeeDB employeeDB = new EmployeeDB();
        TaskDB taskDB = new TaskDB();
        TaskService taskService = new TaskService(employeeDB, taskDB);
        // add employee Andrzej...
        Employee employee = new Employee("Andrzej");
        long employeeId = employeeDB.add(employee);
        // ... and task fixing bug to the database
        Task task = new Task("fixing bug");
        long taskId = taskDB.add(task);

        // when
        taskService.assignTaskToEmployee(taskId, employeeId);
        Optional<Task> employeeTask = taskService.getEmployeeTask(employeeId);

        /// then
        assertThat(employeeTask.get().getTitle()).isEqualTo(task.getTitle());
    }
}