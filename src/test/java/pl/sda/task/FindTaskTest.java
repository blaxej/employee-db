package pl.sda.task;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.sda.DbTestUtils;
import pl.sda.TaskService;
import pl.sda.employee.Employee;
import pl.sda.employee.EmployeeDb;

import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class FindTaskTest {
    @DisplayName("given some tasks  " +
            "when find by type,prioryty,description and all" +
            "then return match task ")
    @Test
    void findTasks() throws Exception {
        //given
        TaskDB taskDB = DbTestUtils.emptyFileTaskDB();
        Task task = new Task("Fix bug", "shit happens", "Bug", true);
        Task task1 = new Task("Fix bug1", "shit happens1", "Bug1", false);
        Task task2 = new Task("Fix bug2", "shit happens2", "Bug2", true);
        taskDB.add(task);
        taskDB.add(task1);
        taskDB.add(task2);
        // when
        Iterable<Task> all = taskDB.findAll();
        Optional<Task> shit_happens = taskDB.findByDescription("shit happens");
        Optional<Task> byPrioryty = taskDB.findByPrioryty(false);
        Optional<Task> bug2 = taskDB.findByType("Bug2");

        /// then
        assertThat(all).hasSize(3);
        assertThat(shit_happens.get().getDescription()).isEqualTo(task.getDescription());
        assertThat(byPrioryty.get().isPriority()).isFalse();
        assertThat(bug2.get().getType()).isEqualTo(task2.getType());
    }


}
