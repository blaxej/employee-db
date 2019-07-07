package pl.sda;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class AssigninTasksTest {

    @DisplayName("given employee without task" +
            "when assigne task to employee" +
            "then employee is working on this task")
    @Test
    void addTaskToEmployee() {
        //given
        EmployeeDb employeeDB = DbTestUtils.emptyInMemoryEmployeeDb();
        TaskDB taskDB = DbTestUtils.emptyInMemmoryTaskDb();
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

    @DisplayName("given task assigned to employee" +
            "when trying to assign this task to another employee" +
            "then assign fail")
    @Test
    void assignBusyTaskToAnotherEmployeeFail() {
        //given
        EmployeeDb employeeDB = DbTestUtils.emptyInMemoryEmployeeDb();
        TaskDB taskDB = DbTestUtils.emptyInMemmoryTaskDb();
        TaskService taskService = new TaskService(employeeDB, taskDB);
        Task task = new Task("fixing bug");
        // add employee Andrzej...
        Employee employeeWithTask = new Employee("Andrzej");
        Employee employeeWithoutTask = new Employee("Bożydar");
        long employeeWithTaskId = employeeDB.add(employeeWithTask);
        long employeeWithoutTaskId = employeeDB.add(employeeWithoutTask);
        long taskId = taskDB.add(task);
        taskService.assignTaskToEmployee(taskId, employeeWithTaskId);

        //when
        TaskAlreadyAssignedException exception = catchThrowableOfType(
                () -> taskService.assignTaskToEmployee(taskId, employeeWithoutTaskId),
                TaskAlreadyAssignedException.class);

        //then
        assertThat(exception).isNotNull();
        assertThat(taskService.getEmployeeTask(employeeWithoutTaskId)).isEmpty();
    }

    @DisplayName("given employee with task" +
            "when try assign new task to this employee" +
            "then assign will fail")
    @Test
    void assignTaskToEmployeeWithTaskShouldFail() {
        //given
        EmployeeDb employeeDB = DbTestUtils.emptyInMemoryEmployeeDb();
        TaskDB taskDB = DbTestUtils.emptyInMemmoryTaskDb();
        TaskService taskService = new TaskService(employeeDB, taskDB);
        Task firstTask = new Task("fixing bug");
        Task secondTask = new Task("it depends");
        // add employee Andrzej...
        Employee employee = new Employee("Andrzej");
        long employeeId = employeeDB.add(employee);
        long firstTaskId = taskDB.add(firstTask);
        long secondTaskId = taskDB.add(secondTask);
        taskService.assignTaskToEmployee(firstTaskId, employeeId);
        //when
        EmployeeAlreadyBusyException exception = catchThrowableOfType(
                () -> taskService.assignTaskToEmployee(secondTaskId, employeeId), EmployeeAlreadyBusyException.class);
        //then
        assertThat(exception).isNotNull();
        assertThat(taskService.getEmployeeTask(employeeId).get().getTitle()).isEqualTo("fixing bug");
    }
}