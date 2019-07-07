package pl.sda.employee;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.sda.DbTestUtils;
import pl.sda.employee.Employee;
import pl.sda.employee.EmployeeDb;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

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
	void add() throws Exception {
		//given
		EmployeeDb employeeDB = emptyDb();
		Employee wojtek = new Employee("Wojtek");

		//when
		employeeDB.add(wojtek);

		//then
		Iterable<Employee> allEmployees = employeeDB.findAll();
		assertThat(allEmployees).hasSize(1);
		assertThat(allEmployees.iterator().next().getName()).isEqualTo("Wojtek");

	}

	private EmployeeDb emptyDb() throws IOException {
		//w zaleznosci od tego co ponizej jest w komentarzu,
		//metoda testowa sprawdza dana implementacje bazy albo w pamieci, albo w pliku.
		return DbTestUtils.emptyInMemoryEmployeeDb();
		//return new FileEmployeeDb(Files.createTempFile("employee-db", ".txt"), Charset.forName("UTF-8"));
	}
}
