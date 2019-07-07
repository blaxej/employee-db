package pl.sda;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FindByNameTest {
	// @formatter:off
	@DisplayName(
		"given Kamila, Karolina and Arek in database, " +
		"when find by name Karolina, " +
		"then only Karolina is found"
	)
	// @formatter:on
	@Test
	void find() {
		//given
		EmployeeDB employeeDB = new EmployeeDB();
		Employee employee1 = new Employee("Karolina");
		Employee employee2 = new Employee("Kamila");
		Employee employee3 = new Employee("Arek");
		employeeDB.add(employee1);
		employeeDB.add(employee2);
		employeeDB.add(employee3);

		//when

		Iterable<Employee> returned = employeeDB.find("Karolina");


		//then
		assertThat(returned).hasSize(1);
		assertThat(returned.iterator().next().getName()).isEqualTo("Karolina");
	}

}
