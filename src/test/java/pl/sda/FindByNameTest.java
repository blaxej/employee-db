package pl.sda;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FindByNameTest {
	// @formatter:off
	@DisplayName(
		"given Kamila, Karolina and Arek in database, " +
		"when findAllByName by name Karolina, " +
		"then only Karolina is found"
	)
	// @formatter:on
	@Test
	void find() {
		//given
		EmployeeDb employeeDB = emptyDb();
		Employee employee1 = employeeWithName("Karolina");
		Employee employee2 = employeeWithName("Kamila");
		Employee employee3 = employeeWithName("Arek");
		employeeDB.add(employee1);
		employeeDB.add(employee2);
		employeeDB.add(employee3);

		//when
		Iterable<Employee> returned = employeeDB.findAllByName("Karolina");

		//then
		assertThat(returned).hasSize(1);
		assertThat(returned.iterator().next().getName()).isEqualTo("Karolina");
	}

	private Employee employeeWithName(String name) {
		return new Employee(name);
	}

	private EmployeeDb emptyDb() {
		return DbTestUtils.emptyInMemoryEmployeeDb();
	}

}
