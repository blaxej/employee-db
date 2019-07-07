package pl.sda.employee;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.sda.DbTestUtils;
import pl.sda.employee.Employee;
import pl.sda.employee.EmployeeDb;

import static org.assertj.core.api.Assertions.assertThat;

public class AddEmployeeTest {
	// @formatter:off
	@DisplayName(
		"Given user Wojtek " +
		"when add Wojtek to database" +
		"then Wojtek can be found in the list of all users"
	)
	// @formatter:on
	@Test
	void add() {
		//given
		EmployeeDb employeeDB = DbTestUtils.emptyInMemoryEmployeeDb();
		Employee wojtek = new Employee("Wojtek");

		//when
		employeeDB.add(wojtek);

		//then
		Iterable<Employee> allEmployees = employeeDB.findAll();
		assertThat(allEmployees).hasSize(1);
		assertThat(allEmployees.iterator().next().getName()).isEqualTo("Wojtek");

	}
}
