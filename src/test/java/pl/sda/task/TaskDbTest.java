package pl.sda.task;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.sda.DbTestUtils;
import pl.sda.task.Task;
import pl.sda.task.TaskDB;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class TaskDbTest {

    @DisplayName("when add 3 tasks in db, then generated ids are unique")
    @Test
    void generateId() throws Exception {
        // given
        TaskDB taskDB = emptyTaskDb();

        // when
        long task1Id = taskDB.add(new Task());
        long task2Id = taskDB.add(new Task());
        long task3Id = taskDB.add(new Task());

        // then
        assertThat(task1Id).isNotEqualTo(task2Id).isNotEqualTo(task3Id);
    }

    private TaskDB emptyTaskDb() {
        return DbTestUtils.emptyInMemmoryTaskDb();
    }

    // @formatter:off
	@DisplayName(
		"given empty task db" +
		"when add new task" +
		"then db contains only task with this title")
	// @formatter:on
    @Test
    void add() {
        //given
        TaskDB taskDB = emptyTaskDb();
        Task task = DbTestUtils.taskWithTitle("title");
        //when
        taskDB.add(task);
        Iterable<Task> result = taskDB.findAll();
        //then
        assertThat(result).hasSize(1);
        assertThat(result.iterator().next().getTitle())
                .isEqualTo("title");
    }

    // @formatter:off
	@DisplayName("given taskdb with few tasks" +
		"when findAllByName by ID of the second task" +
		"then the second task is returned")
	// @formatter:on
    @Test
    void findById() {
        //given
        TaskDB taskDB = emptyTaskDb();
        Task task1 = DbTestUtils.taskWithTitle("task1 title");
        taskDB.add(task1);
        Task task2 = DbTestUtils.taskWithTitle("task2 title");
        long task2Id = taskDB.add(task2);
        Task task3 = DbTestUtils.taskWithTitle("task3 title");
        taskDB.add(task3);

        //when
        Optional<Task> foundTask = taskDB.findById(task2Id);

        //then
        assertThat(foundTask.get().getId()).isEqualTo(task2Id);
        assertThat(foundTask.get().getTitle())
                .isEqualTo(task2.getTitle());
    }


}
